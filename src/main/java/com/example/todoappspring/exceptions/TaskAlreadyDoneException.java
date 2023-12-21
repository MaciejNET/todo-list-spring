package com.example.todoappspring.exceptions;

public final class TaskAlreadyDoneException extends ToDoAppException {
    public TaskAlreadyDoneException() {
        super("This task is already done.");
    }
}
