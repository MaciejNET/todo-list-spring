package com.example.todoappspring.exceptions;

public class ToDoListNotFoundException extends ToDoAppException {
    public ToDoListNotFoundException(final Long id) {
        super("ToDo List with ID : '" + id + "' does not exists.");
    }
}
