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
public class ParseTaskLog implements Serializable {
    private Integer id;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 读取sql
     */
    private String readSql;

    /**
     * 解析条数
     */
    private Integer parseCount;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 执行日志
     */
    private String executeLog;
}
