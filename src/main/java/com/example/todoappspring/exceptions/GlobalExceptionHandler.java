package com.example.todoappspring.exceptions;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error(e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(ToDoAppException.class)
    public ResponseEntity<String> handleToDoAppException(ToDoAppException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
