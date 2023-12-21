package com.example.todoappspring.exceptions;

public class TaskAlreadyExistsException extends ToDoAppException {
    public TaskAlreadyExistsException() {
        super("Task with the same name already exists and is not done.");
    }
}
