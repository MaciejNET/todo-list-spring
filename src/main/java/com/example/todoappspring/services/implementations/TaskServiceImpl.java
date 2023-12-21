package com.example.todoappspring.services.implementations;

import com.example.todoappspring.commands.AddTaskToListCommand;
import com.example.todoappspring.commands.MarkTaskAsDoneCommand;
import com.example.todoappspring.entities.Task;
import com.example.todoappspring.entities.ToDoList;
import com.example.todoappspring.exceptions.TaskAlreadyDoneException;
import com.example.todoappspring.exceptions.TaskNotFoundException;
import com.example.todoappspring.exceptions.ToDoListNotFoundException;
import com.example.todoappspring.repositories.TaskRepository;
import com.example.todoappspring.repositories.ToDoListRepository;
import com.example.todoappspring.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final ToDoListRepository toDoListRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(ToDoListRepository toDoListRepository, TaskRepository taskRepository) {
        this.toDoListRepository = toDoListRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTaskToToDoList(AddTaskToListCommand command) throws Exception {
        Optional<ToDoList> toDoList = toDoListRepository.findById(command.id());
        if (toDoList.isEmpty()) {
            throw new ToDoListNotFoundException(command.id());
        }
        Task task = new Task(command.name(), toDoList.get());
        toDoList.get().addTask(task);
        toDoListRepository.save(toDoList.get());
    }

    @Override
    public void markTaskAsDone(MarkTaskAsDoneCommand command) throws Exception {
        Optional<Task> task = taskRepository.findById(command.id());
        if (task.isEmpty()) {
            throw new TaskNotFoundException(command.id());
        }
        task.get().done();
        taskRepository.save(task.get());
    }
}
