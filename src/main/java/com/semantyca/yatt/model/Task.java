package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.semantyca.yatt.controller.converter.AssigneeDeserializer;
import com.semantyca.yatt.model.constant.StageType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task  extends SecureAppEntity<UUID> {
    private StatusType status = StatusType.UNKNOWN;
    private StageType stage = StageType.UNKNOWN;
    private TaskType type = TaskType.UNKNOWN;
    private Assignee assignee;

    @NotBlank(message = "{description_is_empty}")
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
        if (assignee != null) {
            return assignee.id;
        }
        return null;
    }

    @JsonDeserialize(using = AssigneeDeserializer.class)
    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }


    public void setAssigneeId(String id) {
        if (assignee == null) {
            assignee = new Assignee();
            assignee.setId(UUID.fromString(id));
        }

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

    public int setTypeCode(int code) {
        this.type = TaskType.getType(code);
        return code;
    }

    public int getTypeCode() {
        return type.getCode();
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    //@JsonFormat(pattern="dd.MM.yyyy HH:mm")
    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }
}
