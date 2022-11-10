package com.zr.parsedata.rowMapper;

import com.zr.parsedata.po.ParseConfig;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParseConfigRowMapper implements RowMapper<ParseConfig> {

    @Override
    public ParseConfig mapRow(ResultSet rs, int num) throws SQLException {
        Integer id = rs.getInt("id");
        Integer typeGroupId = rs.getInt("type_group_id");
        Integer titleGroupId = rs.getInt("title_group_id");
        String titleGroupName = rs.getString("title_group_name");
        String name = rs.getString("name");
        String nameSign = rs.getString("name_sign");
        Integer labelType = rs.getInt("label_type");
        String labelName = rs.getString("label_name");
        String expression = rs.getString("expression");
        String valueSplicer = rs.getString("value_splicer");
        String cycleSplicer = rs.getString("cycle_splicer");
        Integer groupSort = rs.getInt("group_sort");
        Integer sort = rs.getInt("sort");
        ParseConfig parseConfig = new ParseConfig();
        parseConfig.setId(id);
        parseConfig.setTypeGroupId(typeGroupId);
        parseConfig.setTitleGroupId(titleGroupId);
        parseConfig.setTitleGroupName(titleGroupName);
        parseConfig.setName(name);
        parseConfig.setNameSign(nameSign);
        parseConfig.setLabelType(labelType);
        parseConfig.setLabelName(labelName);
        parseConfig.setExpression(expression);
        parseConfig.setValueSplicer(valueSplicer);
        parseConfig.setCycleSplicer(cycleSplicer);
        parseConfig.setGroupSort(groupSort);
        parseConfig.setSort(sort);
        return parseConfig;
    }
}