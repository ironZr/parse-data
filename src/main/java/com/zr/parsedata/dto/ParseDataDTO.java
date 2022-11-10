package com.zr.parsedata.dto;

import com.zr.parsedata.common.validatedGroup.ValidatedGroups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 解析DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class ParseDataDTO implements Serializable {


    /**
     * 批量的话 该条数据唯一标识
     */
    @NotBlank(message = "flag不能为空", groups = {ValidatedGroups.Other.class})
    private String flag;

    /**
     * 原始数据
     */
    @NotBlank(message = "originData不能为空", groups = {ValidatedGroups.Select.class, ValidatedGroups.Other.class})
    private String originData;

    /**
     * 类型id =》对应parse_type表id
     */
    @NotNull(message = "typeId不能为空", groups = {ValidatedGroups.Select.class, ValidatedGroups.Other.class})
    private Integer typeId;

}
