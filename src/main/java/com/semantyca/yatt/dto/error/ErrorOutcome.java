package com.semantyca.yatt.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorOutcome extends AbstractOutcome<ErrorOutcome> {
    protected OutcomeType type = OutcomeType.HARD_ERROR;

    @JsonIgnore
    public String getPageName() {
        return pageName;
    }


}
