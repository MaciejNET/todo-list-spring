package com.example.todoappspring.services;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.exceptions.*;

import java.util.List;

public interface ToDoListService {
    List<ToDoListDto> getAllToDoLists();

    ToDoListDto getToDoListById(Long id);

    void createToDoList(CreateToDoListCommand command) throws ToDoListAlreadyExistsException;

    void deleteToDoList(Long id) throws ToDoListNotFoundException;
}
