package com.zr.parsedata.service.impl;

import com.zr.parsedata.po.ParseType;
import com.zr.parsedata.rowMapper.ParseTypeRowMapper;
import com.zr.parsedata.service.ParseTypeService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;

@Service
public class ParseTypeServiceImpl implements ParseTypeService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public ParseType getByid(Integer id) {
        Object[] args = new Object[]{id};
        int[] types = new int[]{Types.INTEGER};
        String typeSql = "SELECT * FROM `parse_type` WHERE id= ?";
        return jdbcTemplate.queryForObject(typeSql, args, types, new ParseTypeRowMapper());
    }
}
