package com.example.todoappspring.exceptions;

public class ToDoListAlreadyExistsException extends ToDoAppException {
    public ToDoListAlreadyExistsException(final String name) {
        super("ToDo List with name : '" + name + "' already exists.");
    }
}
