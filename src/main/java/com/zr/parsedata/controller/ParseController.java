package com.zr.parsedata.controller;

import com.zr.parsedata.common.entity.R;
import com.zr.parsedata.common.validatedGroup.ValidatedGroups;
import com.zr.parsedata.dto.ParseDataDTO;
import com.zr.parsedata.service.ParseService;
import com.zr.parsedata.vo.ListParseVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/parse")
public class ParseController {

    @Resource
    private ParseService parseService;

    @PostMapping("/parseToHtml")
    public R<String> parse(@RequestBody @Validated(ValidatedGroups.Select.class) ParseDataDTO parseDataDTO) {
        return R.ok(parseService.parse(parseDataDTO));
    }

    @PostMapping("/listParseToHtml")
    public R<List<ListParseVO>> listParse(@RequestBody @Validated(ValidatedGroups.Other.class) List<ParseDataDTO> parseDataDTOS) {
        return R.ok(parseService.listParse(parseDataDTOS));
    }

}
