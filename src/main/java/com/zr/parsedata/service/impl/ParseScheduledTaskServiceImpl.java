package com.zr.parsedata.service.impl;

import com.zr.parsedata.po.ParseTask;
import com.zr.parsedata.rowMapper.ParseTaskRowMapper;
import com.zr.parsedata.service.ParseScheduledTaskService;
import com.zr.parsedata.service.ParseService;
import com.zr.parsedata.service.ParseTaskConfigService;
import com.zr.parsedata.service.ParseTaskLogService;
import com.zr.parsedata.thread.ParseXMlThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class ParseScheduledTaskServiceImpl implements ParseScheduledTaskService {

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private ParseService parseService;

    @Resource
    private ParseTaskLogService parseTaskLogService;

    @Resource
    private ParseTaskConfigService parseTaskConfigService;


    //接受任务的返回结果
    private ConcurrentHashMap<Integer, ScheduledFuture<?>> scheduledFutureMap = new ConcurrentHashMap<>(16);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void startTask(Integer id) {
        Object[] args = {id};
        int[] type = {Types.INTEGER};
        ParseTask parseTask = jdbcTemplate.queryForObject("SELECT * FROM `parse_task` WHERE id=?", args, type, new ParseTaskRowMapper());

        //判断是任务否已经在执行
        if (scheduledFutureMap.containsKey(id)) throw new RuntimeException(String.format("定时任务[%s]已经开启,请勿重新开启！", id));


        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(new ParseXMlThread(parseService, jdbcTemplate, parseTaskLogService, parseTaskConfigService, id), new CronTrigger(parseTask.getCron()));
        Optional.ofNullable(schedule).orElseThrow(() -> new RuntimeException(String.format("定时任务[%s]启动失败", id)));

        //存储、修改状态
        scheduledFutureMap.put(id, schedule);
        jdbcTemplate.update("UPDATE `parse_task` SET task_switch=1 WHERE id=?", args, type);

        log.info("开启任务成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopTask(Integer id) {
        Boolean cancel = Optional.ofNullable(scheduledFutureMap.get(id)).map(tar -> tar.cancel(Boolean.TRUE)).orElse(Boolean.FALSE);
        if (Boolean.FALSE.equals(cancel)) throw new RuntimeException(String.format("定时任务[%s]关闭失败", id));
        //删除
        scheduledFutureMap.remove(id);

        Object[] args = {id};
        int[] type = {Types.INTEGER};
        jdbcTemplate.update("UPDATE `parse_task` SET task_switch=0 WHERE id=?", args, type);

        log.info("关闭任务成功！");
    }

}
