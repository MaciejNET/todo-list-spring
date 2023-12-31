package com.example.todoappspring.entities;

import com.example.todoappspring.exceptions.TaskAlreadyExistsException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// ToDoList entity representing a to-do list
@Entity
@Table(name = "todo_list")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // One-to-many relationship with Task entity
    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    // Default constructor required by JPA
    protected ToDoList() {}

    // Constructor to create a new to-do list with a name
    public ToDoList(final String name) {
        this.name = name;
    }

    // Getter for to-do list ID
    public Long getId() {
        return id;
    }

    // Getter for to-do list name
    public String getName() {
        return name;
    }

    // Getter for tasks in the to-do list
    public List<Task> getTasks() {
        return tasks;
    }

    // Method to add a task to the to-do list. Throws an exception if the task already exists and is not done
    public void addTask(Task task) throws Exception {
        if (tasks.stream().anyMatch(x -> x.getName().equals(task.getName()) && !x.isDone())) {
            throw new TaskAlreadyExistsException();
        }

        tasks.add(task);
    }
}