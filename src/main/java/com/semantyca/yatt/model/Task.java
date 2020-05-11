package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.semantyca.yatt.controller.converter.AssigneeDeserializer;
import com.semantyca.yatt.model.constant.PriorityType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task  extends SecureDataEntity<UUID> {
    private PriorityType priority = PriorityType.UNKNOWN;
    private StatusType status = StatusType.UNKNOWN;
    private TaskType type = TaskType.UNKNOWN;
    private Assignee assignee;

    @NotBlank(message = "{description_is_empty}")
    private String description;
    private ZonedDateTime deadline;


    @JsonIgnore
    public PriorityType getPriority() {
        return priority;
    }

    public void setPriorityCode(int code) {
        this.priority = PriorityType.getType(code);
    }

    public int getPriorityCode() {
        return priority.getCode();
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    @JsonIgnore
    public StatusType getStatus() {
        return status;
    }

    public void setStatusCode(int stage) {
        this.status = StatusType.getType(stage);
    }

    public int getStatusCode() {
        return status.getCode();
    }

    public void setStatus(StatusType status) {
        this.status = status;
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
