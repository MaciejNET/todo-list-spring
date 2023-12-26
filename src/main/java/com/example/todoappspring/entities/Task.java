package com.example.todoappspring.entities;

import com.example.todoappspring.exceptions.TaskAlreadyDoneException;
import jakarta.persistence.*;

// Task entity representing a task in a to-do list
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
    private ToDoList todoList;

    // Default constructor required by JPA
    protected Task() {}

    // Constructor to create a new task with a name and associated to-do list
    public Task(final String name, final ToDoList todoList) {
        this.name = name;
        this.todoList = todoList;
        isDone = false;
    }

    // Getter for task ID
    public long getId() {
        return id;
    }

    // Getter for task name
    public String getName() {
        return name;
    }

    // Check if the task is done
    public boolean isDone() {
        return isDone;
    }

    // Mark the task as done. Throws an exception if the task is already done
    public void done() throws Exception {
        if (isDone) {
            throw new TaskAlreadyDoneException();
        }

        isDone = true;
    }
}