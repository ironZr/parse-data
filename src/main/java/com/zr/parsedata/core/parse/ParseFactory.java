package com.zr.parsedata.core.parse;

import com.zr.parsedata.core.parse.enums.ParseEnum;

public class ParseFactory {

    private final static ParseRegistry parseRegistry = new ParseRegistry();

    /**
     * 根据解析规则获取解析handel
     *
     * @param parseWay 解析规则Code
     * @return
     */
    public static Parse getParseWay(String parseWay) {
        ParseEnum parseEnum = ParseEnum.match(parseWay);
        return parseRegistry.getParseWay(parseEnum);
    }

}
