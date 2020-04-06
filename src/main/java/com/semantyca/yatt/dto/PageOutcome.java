package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageOutcome extends Outcome<IPage> {

    public PageOutcome setPayload(IPage result) {
        payload = result;
        type = OutcomeType.PLAIN_PAGE;
        return this;
    }

}
