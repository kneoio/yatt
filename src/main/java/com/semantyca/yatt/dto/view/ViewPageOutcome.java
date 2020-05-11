package com.semantyca.yatt.dto.view;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewPageOutcome extends AbstractOutcome {

     public ViewPageOutcome setPayload(ViewPage viewPage) {
        payloads.put(viewPage.getEntityType(), viewPage);
        type = OutcomeType.VIEW_PAGE;
        return this;
    }

}
