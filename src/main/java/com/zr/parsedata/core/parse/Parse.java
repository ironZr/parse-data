package com.zr.parsedata.core.parse;

import com.zr.parsedata.dto.ParseConfigDTO;

import java.util.List;

public interface Parse {


    /**
     * originData => html
     *`
     * @param originData   数据
     * @param parseConfigs 配置
     * @return
     */
    String parseToHtml(String originData, List<ParseConfigDTO> parseConfigs);


}
