package com.example.todoappspring.services.implementations;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.entities.ToDoList;
import com.example.todoappspring.exceptions.*;
import com.example.todoappspring.mappers.EntityMapper;
import com.example.todoappspring.repositories.ToDoListRepository;
import com.example.todoappspring.services.ToDoListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoListServiceImpl implements ToDoListService {
    private final ToDoListRepository toDoListRepository;
    private final EntityMapper mapper;

    public ToDoListServiceImpl(ToDoListRepository toDoListRepository, EntityMapper mapper) {
        this.toDoListRepository = toDoListRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ToDoListDto> getAllToDoLists() {
        Iterable<ToDoList> toDoLists = toDoListRepository.findAll();
        if (!toDoLists.iterator().hasNext()) {
            return new ArrayList<>();
        }
        List<ToDoListDto> toDoListDtos = new ArrayList<>();
        toDoLists.forEach(toDoList -> {
            List<TaskDto> taskDtos = toDoList.getTasks().stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            ToDoListDto toDoListDto = mapper.toDto(toDoList);
            toDoListDto.setTasks(taskDtos);
            toDoListDtos.add(toDoListDto);
        });
        return toDoListDtos;
    }

    @Override
    public ToDoListDto getToDoListById(final Long id) {
        Optional<ToDoList> toDoList = toDoListRepository.findById(id);
        return toDoList.map(mapper::toDto).orElse(null);
    }

    @Override
    public void createToDoList(CreateToDoListCommand command) throws ToDoListAlreadyExistsException {
        Optional<ToDoList> existingToDoList = toDoListRepository.findByName(command.name());
        if (existingToDoList.isPresent()) {
            throw new ToDoListAlreadyExistsException(command.name());
        }
        ToDoList toDoList = new ToDoList(command.name());
        toDoListRepository.save(toDoList);
    }

    @Override
    public void deleteToDoList(final Long id) throws ToDoListNotFoundException {
        Optional<ToDoList> toDoList = toDoListRepository.findById(id);
        if (toDoList.isEmpty()) {
            throw new ToDoListNotFoundException(id);
        }
        toDoListRepository.deleteById(id);
    }
}
