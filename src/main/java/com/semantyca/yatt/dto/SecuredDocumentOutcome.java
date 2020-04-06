package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.model.SecureAppEntity;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecuredDocumentOutcome extends Outcome<SecureAppEntity> {

    @JsonGetter("payload")
    public Outcome setPayload(SecureAppEntity entity) {
        type = OutcomeType.DOCUMENT;
        this.payload = entity;
        return this;
    }
}
