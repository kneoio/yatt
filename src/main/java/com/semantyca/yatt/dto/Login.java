package com.semantyca.yatt.dto;

public class Login implements IPage {

    private String greetMessage = "welcome dude";

    public String getGreetMessage() {
        return greetMessage;
    }

    public void setGreetMessage(String greetMessage) {
        this.greetMessage = greetMessage;
    }
}
