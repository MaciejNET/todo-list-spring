package com.example.todoappspring.services;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.exceptions.*;

import java.util.List;

/**
 * The ToDoListService interface provides methods for interacting with ToDoList entities.
 * It declares four methods:
 * - getAllToDoLists: This method returns a list of all ToDoListDto objects, which are Data Transfer Objects representing to-do lists.
 * - getToDoListById: This method takes a Long id as a parameter and returns a ToDoListDto object representing the to-do list with the given id.
 * - createToDoList: This method takes a CreateToDoListCommand object as a parameter, which encapsulates the information needed to create a new ToDoList. It throws a ToDoListAlreadyExistsException if a to-do list with the same name already exists.
 * - deleteToDoList: This method takes a Long id as a parameter and deletes the to-do list with the given id. It throws a ToDoListNotFoundException if no to-do list with the given id exists.
 * The actual implementation of these methods would typically involve calling methods on a ToDoListRepository to interact with the database.
 */
public interface ToDoListService {
    List<ToDoListDto> getAllToDoLists();

    ToDoListDto getToDoListById(Long id);

    void createToDoList(CreateToDoListCommand command) throws ToDoListAlreadyExistsException;

    void deleteToDoList(Long id) throws ToDoListNotFoundException;
}
