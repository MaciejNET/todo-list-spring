package com.example.todoappspring.controllers;

import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.MarkTaskAsDoneCommand;
import com.example.todoappspring.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> addTask(@RequestBody AddTaskToListCommand command) throws Exception {
        taskService.addTaskToToDoList(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> markTaskAsDone(@RequestBody MarkTaskAsDoneCommand command) throws Exception {
        taskService.markTaskAsDone(command);
        return ResponseEntity.ok().build();
    }
}
