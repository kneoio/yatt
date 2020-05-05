package com.semantyca.yatt.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"identifier", "type", "title", "payload"})
public class ErrorOutcome<T extends IErrorPage> extends AbstractOutcome<T> implements IErrorPage {

    public AbstractOutcome<T> setPayload(T error){
        type = OutcomeType.HARD_ERROR;
        if (title == null)title = error.getMessage();
        payload = error;
        return this;
    }

    @JsonIgnore
    public String getPageName() {
        return pageName;
    }

    @Override
    @JsonIgnore
    public String getMessage() {
        return title;
    }
}
