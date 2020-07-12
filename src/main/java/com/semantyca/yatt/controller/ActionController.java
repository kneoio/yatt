package com.semantyca.yatt.controller;

import com.semantyca.yatt.dto.constant.MessageLevel;
import com.semantyca.yatt.dto.page.FeedbackEntry;
import com.semantyca.yatt.dto.page.ProcessFeedback;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.exception.RLSIsNotNormalized;
import com.semantyca.yatt.security.SessionUser;
import com.semantyca.yatt.service.AssigneeService;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.service.exception.DocumentAccessException;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {

    @Autowired
    private AssigneeService service;

    @Autowired
    private TaskService taskService;


    @PostMapping("actions/{id}/{action}")
    public ResponseEntity doAction(@PathVariable(value = "id") String id, @PathVariable(value = "action") String actionType) throws DocumentNotFoundException, DocumentAccessException, RLSIsNotNormalized {
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = taskService.findById(id, sessionUser.getUserId());
        task.setStatus(StatusType.IN_PROGRESS);
        Task result = taskService.update(task, sessionUser.getUserId());

        FeedbackEntry feedbackEntry = new FeedbackEntry();
        feedbackEntry.setId(id);

        if (result != null) {
            feedbackEntry.setLevel(MessageLevel.SUCCESS);
            feedbackEntry.setDescription("Implementation of the document has been started");
        } else {
            feedbackEntry.setLevel(MessageLevel.FAILURE);
            feedbackEntry.setDescription("Something wrong happened, document still draft");
        }

        ProcessFeedback feedback = new ProcessFeedback();
        feedback.addEntry(feedbackEntry);
        return ResponseEntity.status(HttpStatus.OK).body(feedback);
    }

}
