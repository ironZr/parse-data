package com.zr.parsedata.rowMapper;

import com.zr.parsedata.vo.ParseTestExampleVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParseTestExampleVORowMapper implements RowMapper<ParseTestExampleVO> {
    @Override
    public ParseTestExampleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String groupName = rs.getString("group_name");

        return ParseTestExampleVO.builder().id(id).name(name).groupName(groupName).build();
    }
}
