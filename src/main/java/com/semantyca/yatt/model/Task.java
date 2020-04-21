package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.semantyca.yatt.controller.converter.AssigneeDeserializer;
import com.semantyca.yatt.model.constant.StageType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Task  extends SecureAppEntity<UUID> {
    private StatusType status;
    private StageType stage;
    private TaskType type;
    private Assignee assignee;
    private String description;
    private ZonedDateTime deadline;

    @JsonIgnore
    public StatusType getStatus() {
        return status;
    }

    public void setStatusCode(int code) {
        this.status = StatusType.getType(code);
    }

    public int getStatusCode() {
        return status.getCode();
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    @JsonIgnore
    public StageType getStage() {
        return stage;
    }

    public void setStageCode(int stage) {
        this.stage = StageType.getType(stage);
    }

    public int getStageCode() {
        return stage.getCode();
    }

    public void setStage(StageType stage) {
        this.stage = stage;
    }

    @JsonIgnore
    public Assignee getAssignee() {
        return assignee;
    }

    public UUID getAssigneeId() {
        return assignee.id;
    }

    @JsonDeserialize(using = AssigneeDeserializer.class)
    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public TaskType getType() {
        return type;
    }

    public void setTypeCode(int code) {
        this.type = TaskType.getType(code);
    }

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
