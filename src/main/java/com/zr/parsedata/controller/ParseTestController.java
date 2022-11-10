package com.zr.parsedata.controller;

import com.zr.parsedata.common.entity.R;
import com.zr.parsedata.service.ParseTestService;
import com.zr.parsedata.vo.ParseTestExampleVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/parseTest")
public class ParseTestController {

    @Resource
    private ParseTestService parseTestService;

    @GetMapping("/list")
    public R<List<ParseTestExampleVO>> list() {
        return R.ok(parseTestService.list());
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<String> show(@PathVariable @NotNull(message = "id不能为空") Integer id) {
        return ResponseEntity.ok(parseTestService.show(id));
    }

    @GetMapping("/formatToJson/{id}")
    public ResponseEntity<String> formatToJson(@PathVariable @NotNull(message = "id不能为空") Integer id) {
        return ResponseEntity.ok(parseTestService.formatToJson(id));
    }

}
