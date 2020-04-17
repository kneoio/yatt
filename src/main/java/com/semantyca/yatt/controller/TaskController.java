package com.semantyca.yatt.controller;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.DefaultOutcome;
import com.semantyca.yatt.dto.document.SecuredDocumentOutcome;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.dto.view.ViewPageOutcome;
import com.semantyca.yatt.model.AnonymousUser;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("tasks")
    public @ResponseBody
    AbstractOutcome getAll(String pageNum, String pageSize){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int reader = AnonymousUser.ID;
        long count = service.getCountOfAll(reader);
        int size = NumberUtil.stringToInt(pageSize, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNum, 1);
        List<Task> result = service.findAll(size, NumberUtil.calcStartEntry(num, size), 0);
        return new ViewPageOutcome().setPayload(new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size)).setPageName("all tasks");
    }

    @RequestMapping("tasks/{id}")
    public @ResponseBody
    AbstractOutcome get(@PathVariable(value="id") String id) {
        int reader = AnonymousUser.ID;
        try {
            int userId = NumberUtil.stringToInt(id);
            Task result = service.findById(userId, reader);
            return new SecuredDocumentOutcome().setPayload(result).setPageName("task " + result.getTitle());
        }catch (Exception e){
            return new ErrorOutcome().setPayload(new ApplicationError(e.getMessage()));
        }
    }

    @PostMapping(path = "/tasks", consumes = "application/json", produces = "application/json")
    public @ResponseBody AbstractOutcome post(@RequestBody Task task){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int reader = AnonymousUser.ID;
        long count = service.post(task, reader);
        return new DefaultOutcome().setResult(ResultType.SUCCESS).setPageName("task");
    }

    @PutMapping("tasks")
    public @ResponseBody
    AbstractOutcome put(Task task){
        int reader = AnonymousUser.ID;
        long count = service.put(task, reader);
        return new DefaultOutcome().setResult(ResultType.SUCCESS).setPageName("task");
    }

    @DeleteMapping("tasks")
    public @ResponseBody
    AbstractOutcome delete(Task task){
        int reader = AnonymousUser.ID;
        long count = service.delete(task, reader);
        return new DefaultOutcome().setResult(ResultType.SUCCESS).setPageName("task");
    }
}
