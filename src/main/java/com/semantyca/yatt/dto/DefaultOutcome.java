package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultOutcome extends Outcome<Object> {

    public DefaultOutcome setPayload(Object result) {
        payload = result;
        type = OutcomeType.SAVING_RESULT;
        return this;
    }

}
