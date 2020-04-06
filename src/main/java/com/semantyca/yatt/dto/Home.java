package com.semantyca.yatt.dto;

public class Home implements IPage {

    private String greetMessage = "welcome to Yatt";
    private String info;

    public String getGreetMessage() {
        return greetMessage;
    }

    public void setGreetMessage(String greetMessage) {
        this.greetMessage = greetMessage;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
