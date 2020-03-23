package com.semantyca.yatt.controller;


import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dao.TaskDAO;
import com.semantyca.yatt.dto.ViewPage;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.util.NumberUtil;

import javax.ws.rs.client.Client;
import java.util.List;

public class TaskController {
    private TaskDAO dao;
    private Client restClient;

    public TaskController(TaskDAO dao, Client restClient) {
        this.dao = dao;
        this.restClient = restClient;
    }

    public ViewPage getAll(String pageNumAsText, String pageSizeAsText, long reader){
        long count = dao.getCountOfAll(reader);
        int pageSize = NumberUtil.stringToInt(pageSizeAsText, EnvConst.DEFAULT_PAGE_SIZE);
        int pageNum = NumberUtil.stringToInt(pageNumAsText, 1);
        List<Task> result = dao.findAll(pageSize, NumberUtil.calcStartEntry(pageNum, pageSize), 0);
        return new ViewPage(result, count, NumberUtil.countMaxPage(count, pageSize), pageNum, pageSize);

    }

}
