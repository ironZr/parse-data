package com.zr.parsedata.service.impl;

import com.zr.parsedata.po.ParseTaskLog;
import com.zr.parsedata.service.ParseTaskLogService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ParseTaskLogServiceImpl implements ParseTaskLogService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertLog(ParseTaskLog parseTaskLog) {
        jdbcTemplate.update("INSERT INTO `parse_task_log` ( `task_id`, `read_sql`, `parse_count`, `start_time`, `end_time`, `execute_log` ) VALUES (?,?,?,?,?,?)"
                , parseTaskLog.getTaskId()
                , parseTaskLog.getReadSql()
                , parseTaskLog.getParseCount()
                , parseTaskLog.getStartTime()
                , parseTaskLog.getEndTime()
                , parseTaskLog.getExecuteLog()
        );
    }
}
