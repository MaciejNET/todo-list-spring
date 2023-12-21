package com.example.todoappspring.mappers;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.entities.Task;
import com.example.todoappspring.entities.ToDoList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    TaskDto toDto(Task task);
    ToDoListDto toDto(ToDoList toDoList);
}
