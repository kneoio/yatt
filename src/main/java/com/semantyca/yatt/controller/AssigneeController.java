package com.semantyca.yatt.controller;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.document.DocumentOutcome;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.dto.view.ViewPageOutcome;
import com.semantyca.yatt.model.system.AnonymousUser;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.service.AssigneeService;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssigneeController {

    @Autowired
    private AssigneeService service;

    @GetMapping("assignees")
    public @ResponseBody AbstractOutcome getAll(String pageNum, String pageSize){
        int reader = AnonymousUser.ID;
        long count = service.getCountOfAll(reader);
        int size = NumberUtil.stringToInt(pageSize, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNum, 1);
        List<Assignee> result = service.findAll(size, NumberUtil.calcStartEntry(num, size), 0);
        return new ViewPageOutcome().setPayload(new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size)).setPageName("all assignees");
    }

    @RequestMapping("assignees/{id}")
    public @ResponseBody AbstractOutcome get(@PathVariable(value="id") String id) {
        int reader = AnonymousUser.ID;
        try {
            int userId = NumberUtil.stringToInt(id);
            Assignee result = service.findById(userId, reader);
            return new DocumentOutcome().setPayload(result).setPageName("assignee " + result.getTitle());
        }catch (Exception e){
            return new ErrorOutcome().setPayload(new ApplicationError(e.getMessage()));
        }
    }

}
