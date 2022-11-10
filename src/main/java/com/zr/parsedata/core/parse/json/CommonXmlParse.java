package com.zr.parsedata.core.parse.json;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import com.zr.parsedata.dto.ParseConfigDTO;
import com.zr.parsedata.core.parse.Parse;
import com.zr.parsedata.core.parse.ParseFactory;
import com.zr.parsedata.core.parse.enums.ParseEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CommonXmlParse implements Parse {

    @Override
    public String parseToHtml(String xml, List<ParseConfigDTO> parseConfigs) {
        Parse parseWay = ParseFactory.getParseWay(ParseEnum.COMMON_JSON.getParseWay());
        return parseWay.parseToHtml(xmlToJson(xml), parseConfigs);
    }


    /**
     * 解析xml-json
     *
     * @param xml
     * @return
     */
    public static String xmlToJson(String xml) {
        try {
            xml = XmlUtil.format(xml);
        } catch (Exception e) {
            log.error("【解析xml出错】：{}" + e.getMessage());
            return "【解析xml出错】：" + e.getMessage();
        }
        JSONObject jsonObject = XML.toJSONObject(xml, Boolean.TRUE);
        return jsonObject.toString();
    }
}
