package com.example.todoappspring.controllers;

import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.exceptions.ToDoListAlreadyExistsException;
import com.example.todoappspring.exceptions.ToDoListNotFoundException;
import com.example.todoappspring.services.ToDoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoListController {
    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping
    public ResponseEntity<List<ToDoListDto>> getAll() {
        return ResponseEntity.ok().body(toDoListService.getAllToDoLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoListDto> get(@PathVariable final Long id) {
        ToDoListDto dto = toDoListService.getToDoListById(id);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody CreateToDoListCommand command) throws ToDoListAlreadyExistsException {
        toDoListService.createToDoList(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ToDoListNotFoundException {
        toDoListService.deleteToDoList(id);
        return ResponseEntity.ok().build();
    }
}