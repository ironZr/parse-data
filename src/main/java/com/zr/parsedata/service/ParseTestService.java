package com.zr.parsedata.service;

import com.zr.parsedata.vo.ParseTestExampleVO;

import java.util.List;

public interface ParseTestService {

    /**
     * 获取测试列表
     *
     * @return
     */
    List<ParseTestExampleVO> list();

    /**
     * 展示html
     * @param id id
     * @return
     */
    String show(Integer id);

    /**
     * 解析xml to json
     * @param id
     * @return
     */
    String formatToJson(Integer id);
}
