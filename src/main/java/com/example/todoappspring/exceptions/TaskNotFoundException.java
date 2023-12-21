package com.example.todoappspring.exceptions;

public class TaskNotFoundException extends ToDoAppException{
    public TaskNotFoundException(final Long id) {
        super("Task with id: '" + id + "' does not exists.");
    }
}
