package com.semantyca.yatt.controller;


import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.ViewPage;
import com.semantyca.yatt.model.AnonymousUser;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("tasks")
    public @ResponseBody
    ViewPage getAll(String pageNum, String pageSize){
        int reader = AnonymousUser.ID;
        long count = service.getCountOfAll(reader);
        int size = NumberUtil.stringToInt(pageSize, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNum, 1);
        List<Task> result = service.findAll(size, NumberUtil.calcStartEntry(num, size), 0);
        return new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size);
    }
}
