package com.semantyca.yatt.controller;


import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.error.ApplicationError;
import com.semantyca.yatt.dto.error.ErrorOutcome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class ErrorController {

    @GetMapping("error")
    public @ResponseBody AbstractOutcome error(){
        return new ErrorOutcome().setPayload(new ApplicationError("oops, somthing happend"));
    }

    @PostMapping("error")
    public @ResponseBody AbstractOutcome postError(){
        return new ErrorOutcome().setPayload(new ApplicationError("oops, somthing happend"));
    }
}
