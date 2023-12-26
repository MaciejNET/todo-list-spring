package com.example.todoappspring.DTO;

import java.io.Serializable;

/**
 * The TaskDto class is a Data Transfer Object (DTO) for the Task entity.
 * A DTO is an object that carries data between processes. In the context of a web application like this one, DTOs are used to structure the data that's sent in HTTP responses.
 * This TaskDto class has fields for id, name, and isDone, which correspond to the properties of a Task entity. It also has getters and setters for these fields.
 * The TaskDto class implements Serializable, which means that its instances can be converted to a byte stream and back, which is useful when you need to send objects of this class in HTTP responses.
 */
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
