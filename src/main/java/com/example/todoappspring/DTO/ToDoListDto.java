package com.example.todoappspring.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The ToDoListDto class is a Data Transfer Object (DTO) for the ToDoList entity.
 * A DTO is an object that carries data between processes. In the context of a web application like this one, DTOs are used to structure the data that's sent in HTTP responses.
 * This ToDoListDto class has fields for id, name, tasks, and tasksDone, which correspond to the properties of a ToDoList entity. It also has getters and setters for these fields.
 * The tasks field is a list of TaskDto objects, representing the tasks in the to-do list. The tasksDone field is a count of the tasks in the to-do list that are marked as done.
 * The ToDoListDto class implements Serializable, which means that its instances can be converted to a byte stream and back, which is useful when you need to send objects of this class in HTTP responses.
 */
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
