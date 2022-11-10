package com.zr.parsedata.thread;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.zr.parsedata.core.parse.constant.ExpressionConstant;
import com.zr.parsedata.dto.ParseDataDTO;
import com.zr.parsedata.po.ParseTaskConfig;
import com.zr.parsedata.po.ParseTaskLog;
import com.zr.parsedata.service.ParseService;
import com.zr.parsedata.service.ParseTaskConfigService;
import com.zr.parsedata.service.ParseTaskLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 任务线程
 */
@Slf4j
public class ParseXMlThread implements Runnable {

    private String readIncrementalMaxFlag = "";

    private Integer taskId;

    private ParseService parseService;

    private ParseTaskLogService parseTaskLogService;

    private ParseTaskConfigService parseTaskConfigService;

    private JdbcTemplate jdbcTemplateOrigin;

    private volatile Boolean flag = Boolean.TRUE;

    public ParseXMlThread(ParseService parseService, JdbcTemplate jdbcTemplateOrigin, ParseTaskLogService parseTaskLogService, ParseTaskConfigService parseTaskConfigService, Integer taskId) {
        this.taskId = taskId;
        this.parseService = parseService;
        this.jdbcTemplateOrigin = jdbcTemplateOrigin;
        this.parseTaskLogService = parseTaskLogService;
        this.parseTaskConfigService = parseTaskConfigService;
    }

    @Override
    public void run() {
        ParseTaskLog.ParseTaskLogBuilder logBuilder = ParseTaskLog.builder();
        AtomicInteger parseCount = new AtomicInteger(0);
        String log = "执行成功！";
        ParseTaskConfig parseTaskConfig = parseTaskConfigService.getByid(taskId);
        try {
            SimpleDataSource dataSource = new SimpleDataSource(parseTaskConfig.getJdbcUrl(), parseTaskConfig.getUserName(), parseTaskConfig.getPassWord());
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            //队列容量
            int produceCapity = 100_000;
            //写入数量
            int consumeCapity = 200;
            //存放线程
            List<CompletableFuture<Void>> threads = Lists.newArrayListWithCapacity(parseTaskConfig.getDmlThreadNum() + 1);
            //队列
            LinkedBlockingDeque<Object[]> linkedBlockingDeque = Queues.newLinkedBlockingDeque(produceCapity);

            //读取
            CompletableFuture<Void> readThread = read(jdbcTemplate, linkedBlockingDeque, logBuilder, parseTaskConfig);
            threads.add(readThread);

            //DML
            writer(jdbcTemplate, consumeCapity, threads, linkedBlockingDeque, parseCount, parseTaskConfig);

            threads.remove(null);
            CompletableFuture.allOf(threads.toArray(new CompletableFuture[]{})).join();
            //更新增量值
            parseTaskConfigService.updateReadIncrementalMax(readIncrementalMaxFlag, parseTaskConfig.getId());
            flag = Boolean.TRUE;
        } catch (Exception e) {
            log = e.toString();
            e.printStackTrace();
        } finally {
            ParseTaskLog parseTaskLog = logBuilder.taskId(taskId).endTime(DateUtil.now()).parseCount(parseCount.get()).executeLog(log).build();
            parseTaskLogService.insertLog(parseTaskLog);
        }

    }


    /**
     * @param jdbcTemplate        jdbcTemplate
     * @param consumeCapity       写入数量
     * @param threads             存放线程
     * @param linkedBlockingDeque 阻塞队列
     * @param parseCount          计数器
     * @param parseTaskConfig
     */
    private void writer(JdbcTemplate jdbcTemplate, int consumeCapity, List<CompletableFuture<Void>> threads, LinkedBlockingDeque<Object[]> linkedBlockingDeque, AtomicInteger parseCount, ParseTaskConfig parseTaskConfig) {
        IntStream.range(0, parseTaskConfig.getDmlThreadNum()).forEach(i -> {
            CompletableFuture<Void> writerThread = CompletableFuture.runAsync(() -> {
                List<Object[]> writerObj = Lists.newArrayListWithCapacity(consumeCapity);
                List<Object[]> parseObj = new Vector<>(consumeCapity);
                while (Boolean.TRUE.equals(flag) || !linkedBlockingDeque.isEmpty()) {
                    while (writerObj.size() != consumeCapity) {
                        Object[] poll = linkedBlockingDeque.poll();
                        if (Boolean.FALSE.equals(flag) && poll == null) {
                            break;
                        }
                        if (poll != null) {
                            writerObj.add(poll);
                        }
                    }
                    writerObj.forEach(tar -> {
//                    writerObj.parallelStream().forEach(tar -> {
                        String html = parseService.parse(ParseDataDTO.builder().originData(tar[Integer.parseInt(parseTaskConfig.getReadParseCol()) - 1].toString()).typeId(parseTaskConfig.getTypeGroupId()).build());
                        Object[] obj = Arrays.stream(StringUtils.split(parseTaskConfig.getDmlCol(), ExpressionConstant.SEMICOLON))
                                .map(idx ->
                                        ExpressionConstant.QUESTIONMARK.equals(idx) ? html : tar[Integer.parseInt(idx) - 1])
                                .toArray();
                        parseObj.add(obj);
                    });
                    String sql = parseTaskConfig.getDmlSql();
                    //DML操作
                    jdbcTemplate.batchUpdate(sql, parseObj);
                    //计数
                    parseCount.addAndGet(writerObj.size());
                    writerObj.clear();
                    parseObj.clear();
                }
            });
            threads.add(writerThread);
        });
    }

    /**
     * 读取
     *
     * @param jdbcTemplate        jdbcTemplate
     * @param linkedBlockingDeque 阻塞队列
     * @param logBuilder
     * @param parseTaskConfig
     * @return
     */
    private CompletableFuture<Void> read(JdbcTemplate jdbcTemplate, LinkedBlockingDeque<Object[]> linkedBlockingDeque, ParseTaskLog.ParseTaskLogBuilder logBuilder, ParseTaskConfig parseTaskConfig) {
        String readSql;
        try {
            readSql = construction(parseTaskConfig, jdbcTemplateOrigin);
            logBuilder.readSql(readSql).startTime(DateUtil.now());
        } catch (RuntimeException e) {
            flag = Boolean.FALSE;
            return null;
        }
        return CompletableFuture.runAsync(() ->
                jdbcTemplate.query(con -> {
                    PreparedStatement preparedStatement =
                            con.prepareStatement(readSql,
                                    ResultSet.TYPE_FORWARD_ONLY,
                                    ResultSet.CONCUR_READ_ONLY);
                    preparedStatement.setFetchSize(Integer.MIN_VALUE);
                    preparedStatement.setFetchDirection(ResultSet.FETCH_FORWARD);
                    return preparedStatement;
                }, rs -> {
                    do {
                        try {
                            linkedBlockingDeque.put(Arrays.stream(StringUtils.split(parseTaskConfig.getReadCol(), ExpressionConstant.SEMICOLON)).map(idx -> {
                                try {
                                    return rs.getString(Integer.parseInt(idx));
                                } catch (SQLException e) {
                                    log.error("【读取错误】：{}", e.getMessage());
                                    e.printStackTrace();
                                }
                                return "";
                            }).toArray());
                        } catch (InterruptedException e) {
                            log.error("【队列添加错误】：{}", e.getMessage());
                            e.printStackTrace();
                        }
                    } while (rs.next());
                    flag = Boolean.FALSE;
                })
        );
    }

    /**
     * 构建sql
     *
     * @param parseTaskConfig   配置
     * @param jdbcTemplateOrign jdbcTemplate
     * @return
     */
    private String construction(ParseTaskConfig parseTaskConfig, JdbcTemplate jdbcTemplateOrign) throws RuntimeException {
        String max = jdbcTemplateOrign.queryForObject(String.format("SELECT max(%s) from %s", parseTaskConfig.getReadIncremental(), parseTaskConfig.getTableName()), String.class);
        max = Optional.ofNullable(max).orElse("");
        String readSql = parseTaskConfig.getReadSql();
        String readIncremental = parseTaskConfig.getReadIncremental();
        String readIncrementalMax = parseTaskConfig.getReadIncrementalMax();
        if (ObjectUtil.isNotEmpty(readIncrementalMax) && readIncrementalMax.compareTo(max) >= 0) {
            log.info("【parse_task_config-ID:{}】 {} 没有新数据", parseTaskConfig.getId(), DateUtil.now());
            throw new RuntimeException(String.format("【parse_task_config-ID:%s】 %s 没有新数据", parseTaskConfig.getId(), DateUtil.now()));
        }
        readIncrementalMaxFlag = max;
        if (ObjectUtil.isEmpty(readIncrementalMax)) return readSql;
        return ObjectUtil.isNotEmpty(readIncremental) ?
                String.format("SELECT * FROM (%s) AS INCRE WHERE INCRE.%s > %s AND INCRE.%s<= %s", readSql, readIncremental, readIncrementalMax, readIncremental, max)
                : readSql;
    }
}
