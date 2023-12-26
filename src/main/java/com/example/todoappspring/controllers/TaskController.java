package com.example.todoappspring.controllers;

import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.MarkTaskAsDoneCommand;
import com.example.todoappspring.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The TaskController class is a REST controller that handles HTTP requests related to tasks.
 * In the context of this application, it provides endpoints for adding a task to a to-do list and marking a task as done.
 * The @RestController annotation is used to define a controller for handling HTTP requests in a Spring Boot application. It includes the @Controller and @ResponseBody annotations, which means that the return value of the methods is directly written into the HTTP response body.
 * The @RequestMapping("/task") annotation is used to map HTTP requests to the controller based on the path.
 * The TaskController class has a dependency on a TaskService, which is used to perform operations related to tasks. This dependency is injected through the constructor.
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * The addTask method is a POST endpoint for adding a task to a to-do list.
     * The @PostMapping annotation is used to map HTTP POST requests onto this method.
     * The @RequestBody annotation is used to bind the HTTP request body to the AddTaskToListCommand parameter. Spring automatically deserializes the JSON request body into a Java type assuming an appropriate one is specified.
     * The method calls the addTaskToToDoList method of the TaskService, passing the command object as a parameter.
     * If the operation is successful, the method returns an HTTP 200 OK status. If an exception is thrown, the method returns an HTTP 500 Internal Server Error status.
     */
    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody AddTaskToListCommand command) throws Exception {
        taskService.addTaskToToDoList(command);
        return ResponseEntity.ok().build();
    }

    /**
     * The markTaskAsDone method is a PUT endpoint for marking a task as done.
     * The @PutMapping annotation is used to map HTTP PUT requests onto this method.
     * The @RequestBody annotation is used to bind the HTTP request body to the MarkTaskAsDoneCommand parameter. Spring automatically deserializes the JSON request body into a Java type assuming an appropriate one is specified.
     * The method calls the markTaskAsDone method of the TaskService, passing the command object as a parameter.
     * If the operation is successful, the method returns an HTTP 200 OK status. If an exception is thrown, the method returns an HTTP 500 Internal Server Error status.
     */
    @PutMapping
    public ResponseEntity<Void> markTaskAsDone(@RequestBody MarkTaskAsDoneCommand command) throws Exception {
        taskService.markTaskAsDone(command);
        return ResponseEntity.ok().build();
    }
}
