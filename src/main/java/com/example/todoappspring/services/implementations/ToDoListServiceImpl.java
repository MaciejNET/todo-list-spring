package com.example.todoappspring.services.implementations;

import com.example.todoappspring.DTO.TaskDto;
import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.entities.ToDoList;
import com.example.todoappspring.exceptions.*;
import com.example.todoappspring.mappers.EntityMapper;
import com.example.todoappspring.repositories.ToDoListRepository;
import com.example.todoappspring.services.ToDoListService;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// ToDoListServiceImpl is the implementation of the ToDoListService interface.
// It provides the business logic for operations related to to-do lists.
@Service
public class ToDoListServiceImpl implements ToDoListService {
    private final ToDoListRepository toDoListRepository;
    private final EntityMapper mapper;
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(ToDoListServiceImpl.class);

    public ToDoListServiceImpl(ToDoListRepository toDoListRepository, EntityMapper mapper) {
        this.toDoListRepository = toDoListRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ToDoListDto> getAllToDoLists() {
        logger.info("Getting all to-do lists");
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
        logger.info("Got all to-do lists");
        return toDoListDtos;
    }

    @Override
    public ToDoListDto getToDoListById(final Long id) {
        logger.info("Getting to-do list by id");
        Optional<ToDoList> toDoList = toDoListRepository.findById(id);
        logger.info("Got to-do list by id");
        return toDoList.map(mapper::toDto).orElse(null);
    }

    @Override
    public void createToDoList(CreateToDoListCommand command) throws ToDoListAlreadyExistsException {
        logger.info("Creating to-do list");
        Optional<ToDoList> existingToDoList = toDoListRepository.findByName(command.name());
        if (existingToDoList.isPresent()) {
            throw new ToDoListAlreadyExistsException(command.name());
        }
        ToDoList toDoList = new ToDoList(command.name());
        toDoListRepository.save(toDoList);
        logger.info("Created to-do list");
    }

    @Override
    public void deleteToDoList(final Long id) throws ToDoListNotFoundException {
        logger.info("Deleting to-do list");
        Optional<ToDoList> toDoList = toDoListRepository.findById(id);
        if (toDoList.isEmpty()) {
            throw new ToDoListNotFoundException(id);
        }
        toDoListRepository.deleteById(id);
        logger.info("Deleted to-do list");
    }
}
