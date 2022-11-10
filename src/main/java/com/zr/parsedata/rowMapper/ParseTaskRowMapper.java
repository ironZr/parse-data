package com.zr.parsedata.rowMapper;

import com.zr.parsedata.po.ParseTask;

import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;


public class ParseTaskRowMapper implements RowMapper<ParseTask> {

    @Override
    public ParseTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ParseTask.builder()
                .id(rs.getInt("id"))
                .cron(rs.getString("cron"))
                .describe(rs.getString("describe"))
                .taskConfigId(rs.getInt("task_config_id"))
                .taskName(rs.getString("task_name"))
                .taskSwitch(rs.getInt("task_switch"))
                .build();
    }
}
