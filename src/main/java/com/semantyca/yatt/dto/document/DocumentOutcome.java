package com.semantyca.yatt.dto.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.model.DataEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentOutcome extends AbstractOutcome<DocumentOutcome> {
    protected OutcomeType type = OutcomeType.DOCUMENT;

    public DocumentOutcome setPayload(DataEntity entity) {
        payloads.put(entity.getEntityType(), entity);
        return this;
    }
}
