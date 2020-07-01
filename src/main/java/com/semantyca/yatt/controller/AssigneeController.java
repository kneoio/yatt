package com.semantyca.yatt.controller;

import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.document.DocumentOutcome;
import com.semantyca.yatt.dto.view.ViewPageOutcome;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.service.AssigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AssigneeController {

    @Autowired
    private AssigneeService service;

    @GetMapping("assignees")
    public ResponseEntity getAll(String pageNum, String pageSize, @RequestParam(defaultValue = "ENTITY") String pattern) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ViewPageOutcome().setPayload(service.findAll(pageSize, pageNum, pattern)).setPageName("all assignee"));
    }


    @RequestMapping("assignees/{id}")
    public @ResponseBody
    AbstractOutcome get(@PathVariable(value = "id") String id) {
        UUID idAsUUID = UUID.fromString(id);
        Assignee result = service.findById(idAsUUID);
        return new DocumentOutcome().setPayload(result).setPageName("assignee " + result.getTitle());

    }

}
