package com.semantyca.yatt.dto.error;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorOutcome<T extends IErrorPage> extends AbstractOutcome<T> implements IErrorPage {

    public AbstractOutcome<T> setPayload(T error){
        type = OutcomeType.HARD_ERROR;
        if (title == null)title = error.getMessage();
        payload = error;
        return this;
    }

    @Override
    @JsonIgnore
    public String getMessage() {
        return title;
    }
}
