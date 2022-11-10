package com.zr.parsedata.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParseTaskConfig implements Serializable {

    /**
     * id
     */
    private Integer id;


    /**
     * 类型组id
     */
    private Integer typeGroupId;


    /**
     * jdbcurl
     */
    private String jdbcUrl;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 读取sql
     */
    private String readSql;

    /**
     * 读取列
     */
    private String readCol;

    /**
     * 增量字段
     */
    private String readIncremental;

    /**
     * 增量最大值
     */
    private String readIncrementalMax;

    /**
     * dml_sql
     */
    private String readParseCol;

    /**
     * dmlSQL
     */
    private String dmlSql;

    /**
     * 写入列
     */
    private String dmlCol;

    /**
     * 写入线程数
     */
    private Integer dmlThreadNum;


}
