package com.zr.parsedata.service.impl;

import com.zr.parsedata.po.ParseTaskConfig;
import com.zr.parsedata.rowMapper.ParseTaskConfigRowMapper;
import com.zr.parsedata.service.ParseTaskConfigService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;

@Service
public class ParseTaskConfigServiceImpl implements ParseTaskConfigService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void updateReadIncrementalMax(String readIncrementalMax, Integer id) {
        jdbcTemplate.update("UPDATE `parse_task_config` SET read_incremental_max = ? WHERE id =?", readIncrementalMax, id);
    }

    @Override
    public ParseTaskConfig getByid(Integer id) {
        Object[] args = {id};
        int[] type = {Types.INTEGER};
        return jdbcTemplate.queryForObject("SELECT * FROM `parse_task_config` WHERE id=?", args, type, new ParseTaskConfigRowMapper());
    }
}
