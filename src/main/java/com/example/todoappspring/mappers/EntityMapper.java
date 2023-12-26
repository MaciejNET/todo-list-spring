package com.example.todoappspring.mappers;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.entities.Task;
import com.example.todoappspring.entities.ToDoList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The EntityMapper interface provides methods for mapping between entity objects and Data Transfer Objects (DTOs).
 * In the context of this application, it provides methods to map between Task and TaskDto objects, and between ToDoList and ToDoListDto objects.
 * The @Mapper annotation is used to annotate interfaces that are to be processed by MapStruct, a code generator that simplifies the implementation of mappings between Java bean types.
 * The componentModel = "spring" attribute in the @Mapper annotation tells MapStruct to generate a Spring bean for this mapper.
 * The INSTANCE field is a singleton instance of the mapper, which can be used to access the mapper methods.
 */
@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    TaskDto toDto(Task task);
    ToDoListDto toDto(ToDoList toDoList);
}
