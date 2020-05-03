package com.semantyca.yatt.test.controller;

import com.semantyca.yatt.model.Task;

import java.util.UUID;

public class TaskBuilder {
    String description;
    String title;

    public TaskBuilder id(UUID i) {
        return this;
    }

    public TaskBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }


    public Task build() {
        Task entity = new Task();
        entity.setId(UUID.randomUUID());
        entity.setTitle(title);
        entity.setDescription(description);
        return entity;
    }
}
