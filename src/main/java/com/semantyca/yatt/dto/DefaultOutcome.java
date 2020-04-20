package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultOutcome extends AbstractOutcome<Object> {

    public DefaultOutcome setPayload(Object result) {
        payload = result;
        type = OutcomeType.SAVING_RESULT;
        return this;
    }

}
