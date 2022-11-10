package com.zr.parsedata.core.parse;

import com.google.common.collect.Maps;
import com.zr.parsedata.core.parse.enums.ParseEnum;
import com.zr.parsedata.core.parse.json.CommonJsonParse;
import com.zr.parsedata.core.parse.json.CommonXmlParse;

import java.util.Map;

public class ParseRegistry {

    private final Map<ParseEnum, Parse> parseMap = Maps.newHashMapWithExpectedSize(8);

    public ParseRegistry() {
        parseMap.put(ParseEnum.COMMON_JSON, new CommonJsonParse());
        parseMap.put(ParseEnum.COMMON_XMl, new CommonXmlParse());
    }

    public Parse getParseWay(ParseEnum parseEnum) {
        return parseMap.get(parseEnum);
    }


}
