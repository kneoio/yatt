package com.semantyca.juka.controller;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dto.PageOutcome;
import com.semantyca.yatt.dto.constant.PayloadType;
import com.semantyca.yatt.security.SessionUser;
import com.semantyca.yatt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class Dashboard {
    private static final String SUPERVISOR = "ROLE_supervisor";

    @Autowired
    private UserService service;

    @GetMapping("dashboard")
    public ResponseEntity get(Authentication authentication) {
        SessionUser sessionUser = (SessionUser) authentication.getPrincipal();
        if (userHasAuthority(authentication.getAuthorities(), SUPERVISOR)) {
            PageOutcome pageOutcome = new PageOutcome();
            pageOutcome.addPayload(PayloadType.TEXT, EnvConst.APP_ID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pageOutcome);
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
