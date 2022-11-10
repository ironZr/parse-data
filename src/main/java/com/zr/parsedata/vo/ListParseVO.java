package com.zr.parsedata.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListParseVO implements Serializable {

    /**
     * 唯一标识
     */
    private String flag;

    /**
     * html
     */
    private String html;
}
