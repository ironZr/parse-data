package com.zr.parsedata.rowMapper;

import com.zr.parsedata.po.ParseTaskLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParseTaskLogRowMapper implements RowMapper<ParseTaskLog> {

    @Override
    public ParseTaskLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ParseTaskLog.builder()
                .id(rs.getInt("id"))
                .taskId(rs.getInt("task_id"))
                .parseCount(rs.getInt("parse_count"))
                .readSql(rs.getString("read_sql"))
                .startTime(rs.getString("start_time"))
                .endTime(rs.getString("end_time"))
                .executeLog(rs.getString("execute_log"))
                .build();
    }
}
