package com.semantyca.yatt.controller;


import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.PageOutcome;
import com.semantyca.yatt.dto.page.Home;
import com.semantyca.yatt.dto.page.Login;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    @GetMapping("sign_in")
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody AbstractOutcome getSignInPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new PageOutcome().addPayload(new Login()).setPageName("login page");
    }

    /*@GetMapping("do_login")
    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity login(@Valid @RequestBody Credentials credentials){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getUsr(), credentials.getPwd());


        return ResponseEntity.status(HttpStatus.OK).header(JWTConst.TOKEN_HEADER, JWTConst.TOKEN_PREFIX).build();
    }*/

    @GetMapping("user_profile")
    public @ResponseBody
    AbstractOutcome getUserProfile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Home home = new Home();
        home.setInfo("{principal:" + auth.getPrincipal() + ", roles:" + auth.getAuthorities() + "}");
        return new PageOutcome().addPayload(home).setPageName("home page");
    }



}
