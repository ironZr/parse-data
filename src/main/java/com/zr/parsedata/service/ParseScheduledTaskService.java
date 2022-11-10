package com.zr.parsedata.service;


public interface ParseScheduledTaskService {
    /**
     * 开启任务
     * @param id id
     */
    void startTask(Integer id);

    /**
     * 停止任务
     * @param id
     */
    void stopTask(Integer id);
}
