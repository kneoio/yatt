package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.controller.ResultType;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Outcome<T> {
    protected OutcomeType type = OutcomeType.DEFAULT;
    protected String title = EnvConst.APP_ID;
    protected String pageName = "";
    protected T payload;

    public Outcome setTitle(String title) {
        this.title = title;
        return this;
    }

    public abstract Outcome<T> setPayload(T payload);

    public T getPayload(){
        return payload;
    }

    public String getTitle() {
        return title;
    }

    public OutcomeType getType() {
        return type;
    }

    public String getPageName() {
        return pageName;
    }

    public Outcome<T> setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }


    public String toString() {
        return "type=" + type + ", title=" + title;
    }

    public Outcome setResult(ResultType success) {
        type = OutcomeType.SAVING_RESULT;
        return this;
    }
}
