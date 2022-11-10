package com.zr.parsedata.rowMapper;

import com.zr.parsedata.po.ParseTestExample;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParseTestExampleRowMapper implements RowMapper<ParseTestExample> {
    @Override
    public ParseTestExample mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String originData = rs.getString("origin_data");
        Integer typeGroupId = rs.getInt("type_group_id");

        return ParseTestExample.builder().id(id).name(name).typeGroupId(typeGroupId).originData(originData).build();
    }

}
