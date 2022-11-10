package com.zr.parsedata.core.parse.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParseEnum {
    COMMON_JSON("COMMON_JSON", "公共JSON解析"),
    COMMON_XMl("COMMON_XMl", "公共XML解析"),
    ;

    private String parseWay;

    private String desc;


    /**
     * 获取解析方式
     *
     * @param way 解析方式名
     * @return
     */
    public static ParseEnum match(String way) {
        for (ParseEnum parseEnum : ParseEnum.values()) {
            if (parseEnum.getParseWay().equals(way)) {
                return parseEnum;
            }
        }
        throw new RuntimeException("没有此种解析方式！！！");
    }

}
