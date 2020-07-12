package com.semantyca.juka.controller;

import com.semantyca.yatt.dto.page.FeedbackEntry;
import com.semantyca.yatt.dto.page.ProcessFeedback;
import com.semantyca.yatt.model.exception.RLSIsNotNormalized;
import com.semantyca.yatt.security.SessionUser;
import com.semantyca.yatt.service.AssigneeService;
import com.semantyca.yatt.service.TaskService;
import com.semantyca.yatt.service.exception.DocumentAccessException;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ServiceActionController {
    private static final String SUPERVISOR = "ROLE_supervisor";

    @Autowired
    private AssigneeService service;

    @Autowired
    private TaskService taskService;


    @PostMapping("service/{action}")
    public ResponseEntity doAction(Authentication authentication, @PathVariable(value = "id") String id, @PathVariable(value = "action") String actionType) throws DocumentNotFoundException, DocumentAccessException, RLSIsNotNormalized {
        SessionUser sessionUser = (SessionUser) authentication.getPrincipal();
        if (userHasAuthority(authentication.getAuthorities(), SUPERVISOR)) {
            FeedbackEntry feedbackEntry = new FeedbackEntry();
            feedbackEntry.setId(id);
            ProcessFeedback feedback = new ProcessFeedback();
            feedback.addEntry(feedbackEntry);
            return ResponseEntity.status(HttpStatus.OK).body(feedback);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    public static boolean userHasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

}
