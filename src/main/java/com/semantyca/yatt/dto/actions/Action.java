package com.semantyca.yatt.dto.actions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Action {
    private ActionType type;
    private String identifier;

    public void setType(ActionType type) {
        this.type = type;
    }

    public ActionType getType() {
        return type;
    }

    public void setIdentifier(String identifier) {
        type = ActionType.CUSTOM;
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
