package com.semantyca.yatt.model;

import com.semantyca.yatt.model.constant.StageType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;

import java.util.UUID;

public class NewTask extends Task {

    public String getTitle(){
        return "New task";
    }


    public UUID getAssigneeId() {
        return new UUID(0,0);
    }

    public int getStatusCode() {
        return StatusType.NO_ACTION.getCode();
    }

    public int getStageCode() {
        return StageType.DRAFT.getCode();
    }

    public int getTypeCode(int code) {
        return TaskType.DEVELOPING.getCode();
    }
}
