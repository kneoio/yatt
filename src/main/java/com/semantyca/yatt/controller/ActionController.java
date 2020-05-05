package com.semantyca.yatt.controller;

import com.semantyca.yatt.service.AssigneeService;
import com.semantyca.yatt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {

    @Autowired
    private AssigneeService service;

    @Autowired
    private TaskService taskService;


    @GetMapping("actions/start_implementation/{id}")
    public ResponseEntity startImplementation(@PathVariable(value="id") String id){
        //return ResponseEntity.status(HttpStatus.OK).body(new SecuredDocumentOutcome().setPayload(taskService.save(id)));
        return null;
    }

}
