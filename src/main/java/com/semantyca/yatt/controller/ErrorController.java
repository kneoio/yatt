package com.semantyca.yatt.controller;


import com.semantyca.yatt.dto.ErrorOutcome;
import com.semantyca.yatt.dto.Outcome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {

    @GetMapping("error")
    public @ResponseBody Outcome error(){
        return new ErrorOutcome().setPayload("oops, somthing happend");
    }

    @PostMapping("error")
    public @ResponseBody Outcome postError(){
        return new ErrorOutcome().setPayload("oops, somthing happend");
    }
}
