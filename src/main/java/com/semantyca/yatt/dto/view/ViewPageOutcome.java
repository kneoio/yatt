package com.semantyca.yatt.dto.view;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewPageOutcome extends AbstractOutcome<ViewPage> {

     public ViewPageOutcome setPayload(ViewPage viewPage) {
        this.payload = viewPage;
        type = OutcomeType.VIEW_PAGE;
        return this;
    }

}
