package com.example.todoappspring.services;

import com.example.todoappspring.DTO.ToDoListDto;
import com.example.todoappspring.commands.CreateToDoListCommand;
import com.example.todoappspring.entities.ToDoList;
import com.example.todoappspring.exceptions.ToDoListAlreadyExistsException;
import com.example.todoappspring.exceptions.ToDoListNotFoundException;
import com.example.todoappspring.mappers.EntityMapper;
import com.example.todoappspring.mappers.EntityMapperImpl;
import com.example.todoappspring.repositories.ToDoListRepository;
import com.example.todoappspring.services.implementations.ToDoListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ToDoListServiceTests {
    private ToDoListRepository toDoListRepository;
    private ToDoListServiceImpl toDoListService;

    @BeforeEach
    public void setUp() {
        toDoListRepository = Mockito.mock(ToDoListRepository.class);
        EntityMapper mapper = new EntityMapperImpl();
        toDoListService = new ToDoListServiceImpl(toDoListRepository, mapper);
    }

    @Test
    public void testGetAllToDoLists() {
        Iterable<ToDoList> toDoLists = new ArrayList<>();
        when(toDoListRepository.findAll()).thenReturn(toDoLists);

        List<ToDoListDto> result = toDoListService.getAllToDoLists();

        verify(toDoListRepository).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetToDoListById() {
        ToDoList toDoList = new ToDoList("Test ToDoList");
        when(toDoListRepository.findById(any())).thenReturn(Optional.of(toDoList));

        ToDoListDto result = toDoListService.getToDoListById(1L);

        verify(toDoListRepository).findById(1L);
        assertNotNull(result);
    }

    @Test
    public void testCreateToDoList() throws Exception {
        CreateToDoListCommand command = new CreateToDoListCommand("Test ToDoList");
        when(toDoListRepository.findByName(any())).thenReturn(Optional.empty());

        toDoListService.createToDoList(command);

        verify(toDoListRepository).save(any());
    }

    @Test
    public void testCreateToDoListThrowsException() {
        ToDoList existingToDoList = new ToDoList("Test ToDoList");
        when(toDoListRepository.findByName(any())).thenReturn(Optional.of(existingToDoList));

        CreateToDoListCommand command = new CreateToDoListCommand("Test ToDoList");
        assertThrows(ToDoListAlreadyExistsException.class, () -> toDoListService.createToDoList(command));
    }

    @Test
    public void testDeleteToDoList() throws Exception {
        ToDoList toDoList = new ToDoList("Test ToDoList");
        when(toDoListRepository.findById(any())).thenReturn(Optional.of(toDoList));

        toDoListService.deleteToDoList(1L);

        verify(toDoListRepository).deleteById(1L);
    }

    @Test
    public void testDeleteToDoListThrowsException() {
        when(toDoListRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ToDoListNotFoundException.class, () -> toDoListService.deleteToDoList(1L));
    }
}