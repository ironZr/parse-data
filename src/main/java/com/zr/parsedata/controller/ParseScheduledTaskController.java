package com.zr.parsedata.controller;

import com.zr.parsedata.common.entity.R;
import com.zr.parsedata.service.ParseScheduledTaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RequestMapping("/task")
@RestController
public class ParseScheduledTaskController {

    @Resource
    private ParseScheduledTaskService parseScheduledTaskService;

    @GetMapping("/startTask/{id}")
    public R startTask(@PathVariable("id") @NotNull(message = "ids不能为空") Integer id) {
        parseScheduledTaskService.startTask(id);
        return R.ok();
    }


    @GetMapping("/stopTask/{id}")
    public R stopTask(@PathVariable("id") @NotNull(message = "ids不能为空") Integer id) {
        parseScheduledTaskService.stopTask(id);
        return R.ok();
    }


}
