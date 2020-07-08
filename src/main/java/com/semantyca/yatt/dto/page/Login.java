package com.semantyca.yatt.dto.page;

public class Login {

    private String greetMessage = "welcome dude";

    public String getGreetMessage() {
        return greetMessage;
    }

    public void setGreetMessage(String greetMessage) {
        this.greetMessage = greetMessage;
    }

    public String getEntityType() {
        return this.getClass().getSimpleName();
    }
}
