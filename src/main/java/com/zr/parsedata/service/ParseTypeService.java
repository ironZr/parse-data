package com.zr.parsedata.service;

import com.zr.parsedata.po.ParseType;

public interface ParseTypeService {

    /**
     * 根据id获取类型组
     *
     * @param id id
     * @return
     */
    ParseType getByid(Integer id);


}
