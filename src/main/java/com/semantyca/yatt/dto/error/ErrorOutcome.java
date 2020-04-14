package com.semantyca.yatt.dto.error;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorOutcome extends AbstractOutcome<IErrorPage> implements IErrorPage {

    public AbstractOutcome<IErrorPage> setPayload(IErrorPage error){
        type = OutcomeType.ERROR;
        title = error.getMessage();
        payload = error;
        return this;
    }

    @Override
    public String getMessage() {
        return title;
    }
}
