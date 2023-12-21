package com.example.todoappspring.entities;

import com.example.todoappspring.exceptions.TaskAlreadyExistsException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "todo_list")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    protected ToDoList() {}

    public ToDoList(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) throws Exception {
        if (tasks.stream().anyMatch(x -> x.getName().equals(task.getName()) && !x.isDone())) {
            throw new TaskAlreadyExistsException();
        }

        tasks.add(task);
    }
}
