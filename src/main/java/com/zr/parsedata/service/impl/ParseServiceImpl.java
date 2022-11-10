package com.zr.parsedata.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zr.parsedata.dto.ParseConfigDTO;
import com.zr.parsedata.po.ParseConfig;
import com.zr.parsedata.core.parse.Parse;
import com.zr.parsedata.core.parse.ParseFactory;
import com.zr.parsedata.dto.ParseDataDTO;
import com.zr.parsedata.po.ParseType;
import com.zr.parsedata.rowMapper.ParseConfigRowMapper;
import com.zr.parsedata.service.ParseService;
import com.zr.parsedata.service.ParseTypeService;
import com.zr.parsedata.vo.ListParseVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParseServiceImpl implements ParseService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private ParseTypeService parseTypeService;


    @Override
    public String parse(ParseDataDTO parseDataDTO) {
        //查询类型组
        ParseType parseType = parseTypeService.getByid(parseDataDTO.getTypeId());

        //查询配置
        Object[] args = new Object[]{parseDataDTO.getTypeId()};
        int[] types = new int[]{Types.INTEGER};
        String configSql = "SELECT * FROM `parse_config` WHERE type_group_id =? ORDER BY group_sort,sort";
        List<ParseConfig> parseConfigs = jdbcTemplate.query(configSql, args, types, new ParseConfigRowMapper());
        List<ParseConfigDTO> parseConfigDTOS = BeanUtil.copyToList(parseConfigs, ParseConfigDTO.class);

        //获取解析处理器
        Parse parseWay = ParseFactory.getParseWay(parseType.getParseWay());

        //处理
        if (ObjectUtil.isEmpty(parseConfigDTOS)) return "";
        parseConfigDTOS.forEach(tar -> tar.setGroupName(parseType.getGroupName()));
        return parseWay.parseToHtml(parseDataDTO.getOriginData(), parseConfigDTOS);
    }

    @Override
    public List<ListParseVO> listParse(List<ParseDataDTO> parseDataDTOS) {
        return parseDataDTOS.parallelStream()
                .map(tar -> ListParseVO.builder().html(parse(tar)).flag(tar.getFlag()).build())
                .collect(Collectors.toList());
    }


}
