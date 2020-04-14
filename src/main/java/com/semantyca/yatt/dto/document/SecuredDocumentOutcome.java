package com.semantyca.yatt.dto.document;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.dto.AbstractOutcome;
import com.semantyca.yatt.dto.OutcomeType;
import com.semantyca.yatt.model.SecureAppEntity;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecuredDocumentOutcome extends AbstractOutcome<SecureAppEntity> {

    @JsonGetter("payload")
    public AbstractOutcome setPayload(SecureAppEntity entity) {
        type = OutcomeType.DOCUMENT;
        this.payload = entity;
        return this;
    }
}