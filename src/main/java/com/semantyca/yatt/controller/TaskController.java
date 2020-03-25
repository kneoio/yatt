package com.semantyca.yatt.controller;


import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.ViewPage;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;


    public ViewPage getAll(String pageNumAsText, String pageSizeAsText, long reader){
        long count = service.getCountOfAll(reader);
        int pageSize = NumberUtil.stringToInt(pageSizeAsText, EnvConst.DEFAULT_PAGE_SIZE);
        int pageNum = NumberUtil.stringToInt(pageNumAsText, 1);
        List<Task> result = service.findAll(pageSize, NumberUtil.calcStartEntry(pageNum, pageSize), 0);
        return new ViewPage(result, count, NumberUtil.countMaxPage(count, pageSize), pageNum, pageSize);

    }

}
