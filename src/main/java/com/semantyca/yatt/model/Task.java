package com.semantyca.yatt.model;

import com.semantyca.yatt.model.constant.StageType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;

import java.time.ZonedDateTime;

public class Task  extends SecureAppEntity {

    private StatusType status;
    private StageType stage;
    private TaskType type;
    private Assignee assignee;
    private String description;
    private ZonedDateTime deadLine;

    public StatusType getStatus() {
        return status;
    }

    public int getStatusCode() {
        return status.getCode();
    }


    public void setStatus(StatusType status) {
        this.status = status;
    }

    public StageType getStage() {
        return stage;
    }

    public int getStageCode() {
        return stage.getCode();
    }

    public void setStage(StageType stage) {
        this.stage = stage;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public int getTypeCode() {
        return type.getCode();
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public ZonedDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(ZonedDateTime deadLine) {
        this.deadLine = deadLine;
    }
}
