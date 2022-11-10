package com.zr.parsedata.service.impl;

import cn.hutool.json.JSONUtil;
import com.zr.parsedata.core.parse.json.CommonXmlParse;
import com.zr.parsedata.dto.ParseDataDTO;
import com.zr.parsedata.po.ParseTestExample;
import com.zr.parsedata.rowMapper.ParseTestExampleRowMapper;
import com.zr.parsedata.rowMapper.ParseTestExampleVORowMapper;
import com.zr.parsedata.service.ParseService;
import com.zr.parsedata.service.ParseTestService;
import com.zr.parsedata.vo.ParseTestExampleVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;

@Service
public class ParseTestServiceImpl implements ParseTestService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private ParseService parseService;

    @Override
    public List<ParseTestExampleVO> list() {
        String sql = "SELECT a.*,b.group_name FROM `parse_test_example` a INNER JOIN `parse_type` AS b ON a.type_group_id = b.id  ORDER BY a.id DESC LIMIT 20";
        return jdbcTemplate.query(sql, new ParseTestExampleVORowMapper());
    }

    @Override
    public String show(Integer id) {
        ParseTestExample parseTestExampleVO = getByid(id);
        return parseService.parse(ParseDataDTO.builder().typeId(parseTestExampleVO.getTypeGroupId()).originData(parseTestExampleVO.getOriginData()).build());
    }

    @Override
    public String formatToJson(Integer id) {
        ParseTestExample parseTestExampleVO = getByid(id);
        String originData = parseTestExampleVO.getOriginData();
        return JSONUtil.isJson(originData) ? originData : CommonXmlParse.xmlToJson(originData);
    }

    public ParseTestExample getByid(Integer id) {
        Object[] obj = {id};
        int[] type = {Types.INTEGER};
        return jdbcTemplate.queryForObject("SELECT * FROM `parse_test_example` WHERE id=?", obj, type, new ParseTestExampleRowMapper());
    }
}
