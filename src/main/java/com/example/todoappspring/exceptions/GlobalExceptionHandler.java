package com.example.todoappspring.exceptions;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The GlobalExceptionHandler class is a global exception handler that handles exceptions thrown by the application.
 * The @ControllerAdvice annotation is used to define a global exception handler for the application.
 * The GlobalExceptionHandler class has a dependency on a Logger, which is used to log exceptions. This dependency is injected through the constructor.
 * The handleException method is an exception handler for all exceptions. It logs the exception and returns an HTTP 500 Internal Server Error status with the exception message in the HTTP response body.
 * The handleToDoAppException method is an exception handler for ToDoAppException exceptions. It logs the exception and returns an HTTP 400 Bad Request status with the exception message in the HTTP response body.
 */
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
