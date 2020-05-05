package com.semantyca.yatt.controller;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.DefaultOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.dto.document.SecuredDocumentOutcome;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.dto.view.ViewPageOutcome;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.security.SessionUser;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import com.semantyca.yatt.util.NumberUtil;
import com.semantyca.yatt.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("tasks")
    public ResponseEntity getAll(String pageNum, String pageSize){
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ViewPageOutcome().setPayload(service.findAll(pageSize, pageNum, sessionUser.getUserId())).setPageName("all tasks"));
    }

    @GetMapping("my_tasks")
    public ResponseEntity getAllMyTasks(String pageNum, String pageSize){
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long count = service.getCountOfAllMyTasks(sessionUser.getUserId(), sessionUser.getUserId());
        int size = NumberUtil.stringToInt(pageSize, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNum, 1);
        List<Task> result = service.findAllMyTasks(size, NumberUtil.calcStartEntry(num, size), sessionUser.getUserId(), sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ViewPageOutcome().setPayload(new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size)).setPageName("all tasks"));
    }

    @RequestMapping("tasks/{id}")
    public ResponseEntity get(@PathVariable(value="id") String id) {
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            Task result;
            if (id.equalsIgnoreCase("new")){
                result = service.getNewTask(sessionUser.getUserId());
            } else {
                UUID documentId = UUID.fromString(id);
                result = service.findById(documentId, sessionUser.getUserId(), true);
            }
            if (result != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new SecuredDocumentOutcome().setPayload(result).setPageName(result.getTitle()));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (DocumentNotFoundException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            String errorId = "Error:" + StringUtil.getRndText(20);
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorOutcome()
                            .setPageName(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                            .setTitle("Internal server error")
                            .setPayload(new ApplicationError(errorId)));
        }
    }

    @PostMapping(path = "/tasks", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AbstractOutcome> post(@Valid @RequestBody Task task){
        System.out.println(task);
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (task.getId() == null) {
            UUID id = service.post(task, sessionUser.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(new DefaultOutcome()
                    .setIdentifier("saving_of_new_document")
                    .setResult(OutcomeType.INFO, ResultType.SUCCESS)
                    .setPageName(id.toString()));
        } else {
            return putData(task, sessionUser);
        }

    }

    @PutMapping(path = "/tasks", consumes = "application/json", produces = "application/json")
    public ResponseEntity put(@Valid @RequestBody Task task){
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return putData(task, sessionUser);
    }


    @DeleteMapping("/tasks")
    public ResponseEntity delete(Task task){
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long count = service.delete(task, sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new DefaultOutcome().setResult(OutcomeType.INFO, ResultType.SUCCESS));
    }

    private ResponseEntity putData(Task task, SessionUser sessionUser){
        service.put(task, sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(new DefaultOutcome()
                .setIdentifier("saving_of_" + task.getId())
                .setResult(OutcomeType.INFO, ResultType.SUCCESS));
    }


}
