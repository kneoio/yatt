package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorOutcome extends Outcome<String> {

    @Override
    public Outcome<String> setPayload(String error) {
        type = OutcomeType.ERROR;
        payload = error;
        return this;
    }

}
