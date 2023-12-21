package com.example.todoappspring.services;

import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.MarkTaskAsDoneCommand;
import com.example.todoappspring.exceptions.TaskAlreadyDoneException;
import com.example.todoappspring.exceptions.TaskNotFoundException;

public interface TaskService {
    void addTaskToToDoList(AddTaskToListCommand command) throws Exception;

    void markTaskAsDone(MarkTaskAsDoneCommand command) throws Exception;
}
