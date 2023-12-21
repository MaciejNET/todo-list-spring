package com.example.todoappspring.mappers;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.entities.Task;
import com.example.todoappspring.entities.ToDoList;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityMapperImpl implements EntityMapper{
    @Override
    public TaskDto toDto(final Task task) {
        return new TaskDto(task.getId(), task.getName(), task.isDone());
    }

    @Override
    public ToDoListDto toDto(final ToDoList toDoList) {
        return new ToDoListDto(toDoList.getId(), toDoList.getName(), toDoList.getTasks().stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }
}
