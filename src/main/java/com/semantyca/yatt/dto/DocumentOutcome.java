package com.semantyca.yatt.dto;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.model.IAppEntity;

@JsonPropertyOrder({"type", "title", "pageName", "payload"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentOutcome extends Outcome<IAppEntity> {

    @JsonGetter("payload")
    public Outcome setPayload(IAppEntity entity) {
        type = OutcomeType.DOCUMENT;
        this.payload = entity;
        return this;
    }
}
