package com.zr.parsedata.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParseType implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String groupName;

    /**
     * 解析处理器
     */
    private String parseWay;
}
