package com.example.todoappspring.repositories;

import com.example.todoappspring.entities.ToDoList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
    Optional<ToDoList> findByName(final String name);
}
