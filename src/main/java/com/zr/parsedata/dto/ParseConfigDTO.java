package com.zr.parsedata.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParseConfigDTO implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 类型组id
     */
    private Integer typeGroupId;

    /**
     * 标题组id
     */
    private Integer titleGroupId;

    /**
     * 标题组名
     */
    private String titleGroupName;

    /**
     * 标签名字
     */
    private String name;

    /**
     * 标签名字符号
     */
    private String nameSign;

    /**
     * 标签类型
     */
    private Integer labelType;

    /**
     * 标签名字
     */
    private String labelName;

    /**
     * 表达式
     */
    private String expression;

    /**
     * 数据拼接符
     */
    private String valueSplicer;

    /**
     * 循环拼接符
     */
    private String cycleSplicer;

    /**
     * 标题名
     */
    private String groupName;


}
