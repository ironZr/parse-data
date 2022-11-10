package com.zr.parsedata.rowMapper;

import com.zr.parsedata.po.ParseTaskConfig;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParseTaskConfigRowMapper implements RowMapper<ParseTaskConfig> {
    @Override
    public ParseTaskConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ParseTaskConfig.builder()
                .id(rs.getInt("id"))
                .typeGroupId(rs.getInt("type_group_id"))
                .jdbcUrl(rs.getString("jdbc_url"))
                .userName(rs.getString("user_name"))
                .passWord(rs.getString("pass_word"))
                .tableName(rs.getString("table_name"))
                .readSql(rs.getString("read_sql"))
                .readCol(rs.getString("read_col"))
                .readParseCol(rs.getString("read_parse_col"))
                .readIncrementalMax(rs.getString("read_incremental_max"))
                .readIncremental(rs.getString("read_incremental"))
                .dmlSql(rs.getString("dml_sql"))
                .dmlCol(rs.getString("dml_col"))
                .dmlThreadNum(rs.getInt("dml_thread_num"))
                .build();
    }
}
