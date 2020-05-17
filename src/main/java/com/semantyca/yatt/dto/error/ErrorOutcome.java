package com.semantyca.yatt.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"identifier", "type", "title", "pageName", "payloads"})
public class ErrorOutcome extends AbstractOutcome<ErrorOutcome> {
    protected OutcomeType type = OutcomeType.HARD_ERROR;

    @JsonIgnore
    public String getPageName() {
        return pageName;
    }


}
