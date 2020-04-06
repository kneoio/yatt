package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewPageOutcome extends Outcome<ViewPage> {

     public ViewPageOutcome setPayload(ViewPage viewPage) {
        this.payload = viewPage;
        type = OutcomeType.VIEW_PAGE;
        return this;
    }

}
