package com.zr.parsedata.service;


import com.zr.parsedata.po.ParseTaskConfig;

public interface ParseTaskConfigService {

    /**
     * 修改增量数据
     *
     * @param readIncrementalMax readIncrementalMax
     * @param id                 id
     * @return
     */
    void updateReadIncrementalMax(String readIncrementalMax, Integer id);


    /**
     * 根据id查询
     *
     * @param id id
     * @return
     */
    ParseTaskConfig getByid(Integer id);

}
