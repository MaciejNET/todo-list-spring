package com.example.todoappspring.entities;

import com.example.todoappspring.exceptions.TaskAlreadyDoneException;
import jakarta.persistence.*;

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

    protected Task() {}

    public Task(final String name, final ToDoList todoList) {
        this.name = name;
        this.todoList = todoList;
        isDone = false;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void done() throws Exception {
        if (isDone) {
            throw new TaskAlreadyDoneException();
        }

        isDone = true;
    }
}
