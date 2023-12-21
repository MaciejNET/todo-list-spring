package com.example.todoappspring.services;

import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.MarkTaskAsDoneCommand;
import com.example.todoappspring.entities.Task;
import com.example.todoappspring.entities.ToDoList;
import com.example.todoappspring.exceptions.TaskNotFoundException;
import com.example.todoappspring.exceptions.ToDoListNotFoundException;
import com.example.todoappspring.repositories.TaskRepository;
import com.example.todoappspring.repositories.ToDoListRepository;
import com.example.todoappspring.services.implementations.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceTests {
    private ToDoListRepository toDoListRepository;
    private TaskRepository taskRepository;
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        toDoListRepository = Mockito.mock(ToDoListRepository.class);
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskServiceImpl(toDoListRepository, taskRepository);
    }

    @Test
    public void testAddTaskToToDoList() throws Exception {
        ToDoList toDoList = new ToDoList("Test ToDoList");
        when(toDoListRepository.findById(any())).thenReturn(Optional.of(toDoList));

        AddTaskToListCommand command = new AddTaskToListCommand(1L, "Test Task");
        taskService.addTaskToToDoList(command);

        verify(toDoListRepository).save(toDoList);
    }

    @Test
    public void testAddTaskToToDoListThrowsException() {
        when(toDoListRepository.findById(any())).thenReturn(Optional.empty());

        AddTaskToListCommand command = new AddTaskToListCommand(1L, "Test Task");
        assertThrows(ToDoListNotFoundException.class, () -> taskService.addTaskToToDoList(command));
    }

    @Test
    public void testMarkTaskAsDone() throws Exception {
        Task task = new Task("Test Task", new ToDoList("Test ToDoList"));
        when(taskRepository.findById(any())).thenReturn(Optional.of(task));

        MarkTaskAsDoneCommand command = new MarkTaskAsDoneCommand(1L);
        taskService.markTaskAsDone(command);

        verify(taskRepository).save(task);
    }

    @Test
    public void testMarkTaskAsDoneThrowsException() {
        when(taskRepository.findById(any())).thenReturn(Optional.empty());

        MarkTaskAsDoneCommand command = new MarkTaskAsDoneCommand(1L);
        assertThrows(TaskNotFoundException.class, () -> taskService.markTaskAsDone(command));
    }
}