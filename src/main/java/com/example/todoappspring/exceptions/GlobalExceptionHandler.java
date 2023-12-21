package com.example.todoappspring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(ToDoAppException.class)
    public ResponseEntity<String> handleToDoAppException(ToDoAppException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
