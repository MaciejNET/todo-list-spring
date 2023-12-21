package com.example.todoappspring.repositories;

import com.example.todoappspring.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
