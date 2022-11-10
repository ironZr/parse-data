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
public class ParseTask implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 任务配置id
     */
    private Integer taskConfigId;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * cron
     */
    private String cron;

    /**
     * 开关  0 关闭   1开启
     */
    private Integer taskSwitch;

    /**
     * 描述
     */
    private String describe;


}
