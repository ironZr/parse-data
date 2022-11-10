package com.zr.parsedata.service;

import com.zr.parsedata.dto.ParseDataDTO;
import com.zr.parsedata.vo.ListParseVO;

import java.util.List;

public interface ParseService {
    /**
     * 解析（xml/json） =》 html
     *
     * @param parseDataDTO 解析DTO
     * @return
     */
    String parse(ParseDataDTO parseDataDTO);

    /**
     * 批量解析（xml/json） =》 html
     *
     * @param parseDataDTOS 解析DTOS
     * @return
     */
    List<ListParseVO>  listParse(List<ParseDataDTO> parseDataDTOS);
}
