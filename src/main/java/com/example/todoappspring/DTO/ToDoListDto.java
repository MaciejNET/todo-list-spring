package com.example.todoappspring.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToDoListDto implements Serializable {
    private Long id;
    private String name;
    private List<TaskDto> tasks;
    private long tasksDone;

    public ToDoListDto() {

    }

    public ToDoListDto(final Long id, final String name, final List<TaskDto> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = Objects.requireNonNullElseGet(tasks, ArrayList::new);
        this.tasksDone = this.tasks.stream().filter(TaskDto::isDone).count();
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

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(final List<TaskDto> tasks) {
        this.tasks = tasks;
    }

    public long getTasksDone() {
        return tasksDone;
    }
}
