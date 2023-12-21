package com.example.todoappspring.DTO;

import java.io.Serializable;

public class TaskDto implements Serializable {
    private Long id;
    private String name;
    private boolean isDone;

    public TaskDto() {

    }

    public TaskDto(final Long id, final String name, final boolean isDone) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(final boolean done) {
        isDone = done;
    }
}
