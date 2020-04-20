package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageOutcome extends AbstractOutcome<IPage> {

    public PageOutcome setPayload(IPage result) {
        payload = result;
        type = OutcomeType.PLAIN_PAGE;
        return this;
    }

}
