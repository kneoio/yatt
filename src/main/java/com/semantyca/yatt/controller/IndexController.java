package com.semantyca.yatt.controller;

import com.semantyca.yatt.dto.Home;
import com.semantyca.yatt.dto.Outcome;
import com.semantyca.yatt.dto.PageOutcome;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("home")
    public @ResponseBody
    Outcome home(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Home home = new Home();
        home.setInfo("{principal:" + auth.getPrincipal() + ", roles:" + auth.getAuthorities() + "}");
        return new PageOutcome().setPayload(home).setPageName("home page");
    }
}
