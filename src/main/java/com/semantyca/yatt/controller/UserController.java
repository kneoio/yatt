package com.semantyca.yatt.controller;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.dto.PageOutcome;
import com.semantyca.yatt.dto.document.DocumentOutcome;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.dto.view.ViewPageOutcome;
import com.semantyca.yatt.model.system.AnonymousUser;
import com.semantyca.yatt.model.system.User;
import com.semantyca.yatt.security.SessionUser;
import com.semantyca.yatt.service.UserService;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("users")
    public ResponseEntity getAll(String pageNum, String pageSize) {
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long count = service.getCountOfAll(sessionUser.getUserId());
        int size = NumberUtil.stringToInt(pageSize, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNum, 1);
        List<User> result = service.findAll(size, NumberUtil.calcStartEntry(num, size), sessionUser.getUserId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ViewPageOutcome().setPayload(new ViewPage(result, count, NumberUtil.countMaxPage(count, size), num, size)).setPageName("all tasks"));
    }

    @RequestMapping("users/{id}")
    public ResponseEntity get(@PathVariable(value = "id") String id) {
        SessionUser sessionUser = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = NumberUtil.stringToInt(id);
        User result = service.findById(userId);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DocumentOutcome().setPayload(result).setPageName("task " + result.getTitle()));
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity post(@RequestBody User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int reader = AnonymousUser.ID;
        if (user.getId() == null) {
            service.post(user, reader);
            return ResponseEntity.status(HttpStatus.OK).body(new PageOutcome()
                    .setIdentifier("saving_of_new_document")
                    .setResult(OutcomeType.INFO, ResultType.SUCCESS)
                    .setTitle("#")
                    .setPageName("task"));
        } else {
            return putData(user, auth);
        }

    }

    @PutMapping(path = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity put(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return putData(user, auth);
    }


    @DeleteMapping("/users")
    public ResponseEntity delete(User user) {
        int reader = AnonymousUser.ID;
        long count = service.delete(user, reader);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new PageOutcome().setResult(OutcomeType.INFO, ResultType.SUCCESS).setPageName("task"));
    }

    private ResponseEntity putData(User user, Authentication authentication) {
        int reader = AnonymousUser.ID;
        service.put(user, reader);
        return ResponseEntity.status(HttpStatus.OK).body(new PageOutcome()
                .setIdentifier("saving_of_" + user.getId())
                .setResult(OutcomeType.INFO, ResultType.SUCCESS)
                .setTitle("#")
                .setPageName("task"));
    }
}
