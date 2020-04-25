package com.semantyca.yatt.controller;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.DefaultOutcome;
import com.semantyca.yatt.dto.document.SecuredDocumentOutcome;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.dto.view.ViewPageOutcome;
import com.semantyca.yatt.model.system.AnonymousUser;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.util.NumberUtil;
import com.semantyca.yatt.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("tasks")
    public ResponseEntity getAll(String pageNum, String pageSize){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int reader = AnonymousUser.ID;
        long count = service.getCountOfAll(reader);
        int size = NumberUtil.stringToInt(pageSize, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNum, 1);
        List<Task> result = service.findAll(size, NumberUtil.calcStartEntry(num, size), 0);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ViewPageOutcome().setPayload(new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size)).setPageName("all tasks"));
    }

    @RequestMapping("tasks/{id}")
    public ResponseEntity get(@PathVariable(value="id") String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            UUID documentId = UUID.fromString(id);
            Task result = service.findById(documentId, 0);
            if (result != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new SecuredDocumentOutcome().setPayload(result).setPageName("task " + result.getTitle()));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (Exception e){
            String errorId = "Error:" + StringUtil.getRndText(20);
            System.out.println(errorId);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorOutcome()
                            .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .setTitle("Internal server error")
                            .setPayload(new ApplicationError(errorId)));
        }
    }

    @PostMapping(path = "/tasks", consumes = "application/json", produces = "application/json")
    public ResponseEntity post(@RequestBody Task task){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int reader = AnonymousUser.ID;
        if (task.getId() == null) {
            service.post(task, reader);
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultOutcome()
                    .setIdentifier("saving_of_new_document")
                    .setResult(ResultType.SUCCESS)
                    .setTitle("#")
                    .setPageName("task"));
        } else {
            return putData(task, auth);
        }

    }

    @PutMapping(path = "/tasks", consumes = "application/json", produces = "application/json")
    public ResponseEntity put(Task task){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return putData(task, auth);
    }


    @DeleteMapping("/tasks")
    public ResponseEntity delete(Task task){
        int reader = AnonymousUser.ID;
        long count = service.delete(task, reader);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new DefaultOutcome().setResult(ResultType.SUCCESS).setPageName("task"));
    }

    private ResponseEntity putData(Task task, Authentication authentication){
        int reader = AnonymousUser.ID;
        service.put(task, reader);
        return ResponseEntity.status(HttpStatus.OK).body(new DefaultOutcome()
                .setIdentifier("saving_of_" + task.getId())
                .setResult(ResultType.SUCCESS)
                .setTitle("#")
                .setPageName("task"));
    }
}
