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
public class ParseTestExample implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 组id
     */
    private Integer typeGroupId;

//    /**
//     * 组名
//     */
//    private String groupName;

    /**
     * 测试名字
     */
    private String name;

    /**
     * 数据
     */
    private String originData;

}
