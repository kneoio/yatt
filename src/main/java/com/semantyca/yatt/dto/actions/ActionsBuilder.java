package com.semantyca.yatt.dto.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"entityType", "actions"})
public class ActionsBuilder {

    private List<Action> actions = new ArrayList<>();

    @JsonIgnore
    public ActionsBuilder addAction(ActionType type){
        Action action = new Action();
        action.setType(type);
        actions.add(action);
        return this;
    }

    @JsonIgnore
    public ActionsBuilder addCustomAction(String identfier){
        Action action = new Action();
        action.setIdentifier(identfier);
        actions.add(action);
        return this;
    }


    public List<Action> build(){
        return actions;
    }


}
