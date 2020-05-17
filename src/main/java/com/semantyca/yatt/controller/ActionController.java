package com.semantyca.yatt.controller;

import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.service.AssigneeService;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.service.exception.DocumentAccessException;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ActionController {

    @Autowired
    private AssigneeService service;

    @Autowired
    private TaskService taskService;


    @PostMapping("actions/start_implementation")
    public ResponseEntity startImplementation(@Valid @RequestBody Task task) throws DocumentNotFoundException, DocumentAccessException {
/*        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setPriority(PriorityType.ON_TIME);
        task.setStatus(StatusType.IN_PROGRESS);
        Task result = taskService.save(task, sessionUser.getUserId());
        AbstractOutcome outcome = new SecuredDocumentOutcome()
                .setPageName("saved at " + task.getRegDate())
                .setPayload(result)
                .setPageName(result.getTitle());
        return ResponseEntity.status(HttpStatus.OK)
                .body(outcome);*/
return null;

    }

}
