package com.example.todoappspring.exceptions;

public abstract class ToDoAppException extends Exception {
    public ToDoAppException(final String message) {
        super(message);
    }
}
