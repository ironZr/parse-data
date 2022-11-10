package com.zr.parsedata.rowMapper;

import com.zr.parsedata.po.ParseType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParseTypeRowMapper implements RowMapper<ParseType> {
        @Override
        public ParseType mapRow(ResultSet rs, int num) throws SQLException {
            Integer id = rs.getInt("id");
            String groupName = rs.getString("group_name");
            String parseWay = rs.getString("parse_way");
            ParseType parseType = new ParseType();
            parseType.setId(id);
            parseType.setGroupName(groupName);
            parseType.setParseWay(parseWay);
            return parseType;
        }
    }