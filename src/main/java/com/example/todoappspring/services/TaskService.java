package com.example.todoappspring.services;

import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.MarkTaskAsDoneCommand;
import com.example.todoappspring.exceptions.TaskAlreadyDoneException;
import com.example.todoappspring.exceptions.TaskNotFoundException;

/**
 * The TaskService interface provides methods for interacting with Task entities.
 * It declares two methods:
 * - addTaskToToDoList: This method takes an AddTaskToListCommand object as a parameter, which encapsulates the information needed to create a new Task and add it to a ToDoList. It throws an Exception if the operation fails.
 * - markTaskAsDone: This method takes a MarkTaskAsDoneCommand object as a parameter, which encapsulates the information needed to mark a Task as done. It throws an Exception if the operation fails, for example if the Task is not found or is already marked as done.
 * The actual implementation of these methods would typically involve calling methods on a TaskRepository to interact with the database.
 */
public interface TaskService {
    void addTaskToToDoList(AddTaskToListCommand command) throws Exception;

    void markTaskAsDone(MarkTaskAsDoneCommand command) throws Exception;
}
