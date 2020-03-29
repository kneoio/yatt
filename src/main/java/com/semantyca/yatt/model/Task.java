package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private ZonedDateTime deadline;

    public StatusType getStatus() {
        return status;
    }

    @JsonIgnore
    public int getStatusCode() {
        return status.getCode();
    }


    public void setStatus(StatusType status) {
        this.status = status;
    }

    public StageType getStage() {
        return stage;
    }

    @JsonIgnore
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

    @JsonIgnore
    public int getTypeCode() {
        return type.getCode();
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }
}
