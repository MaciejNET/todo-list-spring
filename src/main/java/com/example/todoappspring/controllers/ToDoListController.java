package com.example.todoappspring.controllers;

import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.exceptions.ToDoListAlreadyExistsException;
import com.example.todoappspring.exceptions.ToDoListNotFoundException;
import com.example.todoappspring.services.ToDoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ToDoListController class is a REST controller that handles HTTP requests related to to-do lists.
 * In the context of this application, it provides endpoints for getting all to-do lists, getting a to-do list by its id, creating a new to-do list, and deleting a to-do list.
 * The @RestController annotation is used to define a controller for handling HTTP requests in a Spring Boot application. It includes the @Controller and @ResponseBody annotations, which means that the return value of the methods is directly written into the HTTP response body.
 * The @RequestMapping("/todo") annotation is used to map HTTP requests to the controller based on the path.
 * The ToDoListController class has a dependency on a ToDoListService, which is used to perform operations related to to-do lists. This dependency is injected through the constructor.
 */
@RestController
@RequestMapping("/todo")
public class ToDoListController {
    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    /**
     * The getAll method is a GET endpoint for getting all to-do lists.
     * The @GetMapping annotation is used to map HTTP GET requests onto this method.
     * The method calls the getAllToDoLists method of the ToDoListService and returns the result in the HTTP response body.
     * If the operation is successful, the method returns an HTTP 200 OK status. If an exception is thrown, the method returns an HTTP 500 Internal Server Error status.
     */
    @GetMapping
    public ResponseEntity<List<ToDoListDto>> getAll() {
        return ResponseEntity.ok().body(toDoListService.getAllToDoLists());
    }

    /**
     * The get method is a GET endpoint for getting a to-do list by its id.
     * The @GetMapping("/{id}") annotation is used to map HTTP GET requests onto this method, where {id} is a path variable representing the id of the to-do list.
     * The @PathVariable annotation is used to bind the path variable to the Long id parameter.
     * The method calls the getToDoListById method of the ToDoListService, passing the id as a parameter, and returns the result in the HTTP response body.
     * If the operation is successful and a to-do list with the given id exists, the method returns an HTTP 200 OK status. If no to-do list with the given id exists, the method returns an HTTP 404 Not Found status. If an exception is thrown, the method returns an HTTP 500 Internal Server Error status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDto> get(@PathVariable final Long id) {
        ToDoListDto dto = toDoListService.getToDoListById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    /**
     * The add method is a POST endpoint for creating a new to-do list.
     * The @PostMapping annotation is used to map HTTP POST requests onto this method.
     * The @RequestBody annotation is used to bind the HTTP request body to the CreateToDoListCommand parameter. Spring automatically deserializes the JSON request body into a Java type assuming an appropriate one is specified.
     * The method calls the createToDoList method of the ToDoListService, passing the command object as a parameter.
     * If the operation is successful, the method returns an HTTP 200 OK status. If an exception is thrown, the method returns an HTTP 500 Internal Server Error status.
     */
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CreateToDoListCommand command) throws ToDoListAlreadyExistsException {
        toDoListService.createToDoList(command);
        return ResponseEntity.ok().build();
    }

    /**
     * The delete method is a DELETE endpoint for deleting a to-do list.
     * The @DeleteMapping("/{id}") annotation is used to map HTTP DELETE requests onto this method, where {id} is a path variable representing the id of the to-do list.
     * The @PathVariable annotation is used to bind the path variable to the Long id parameter.
     * The method calls the deleteToDoList method of the ToDoListService, passing the id as a parameter.
     * If the operation is successful, the method returns an HTTP 200 OK status. If no to-do list with the given id exists, the method returns an HTTP 404 Not Found status. If an exception is thrown, the method returns an HTTP 500 Internal Server Error status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ToDoListNotFoundException {
        toDoListService.deleteToDoList(id);
        return ResponseEntity.ok().build();
    }
}