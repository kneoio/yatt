package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.controller.ResultType;

@JsonPropertyOrder({"identifier", "type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractOutcome<T> {
    protected String identifier = "undefined";
    protected OutcomeType type = OutcomeType.UNKNOWN;
    protected String title = EnvConst.APP_ID;
    protected String pageName = "";
    protected T payload;

    public AbstractOutcome setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIdentifier() {
        return identifier;
    }

    public AbstractOutcome<T> setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public AbstractOutcome setType(OutcomeType type) {
        this.type = type;
        return this;
    }

    public abstract AbstractOutcome<T> setPayload(T payload);

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

    public AbstractOutcome<T> setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }


    public String toString() {
        return "identifier=" + identifier + ",type=" + type + ", title=" + title;
    }

    public AbstractOutcome setResult(OutcomeType type, ResultType result) {
        this.type = type;
        title = result.name();
        return this;
    }
}
