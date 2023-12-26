package com.example.todoappspring.repositories;

import com.example.todoappspring.entities.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * The TaskRepository interface extends CrudRepository, which provides methods for CRUD operations.
 * CRUD stands for Create, Read, Update, and Delete, which are the four basic functions of persistent storage.
 * In the context of this application, the TaskRepository interface provides methods to interact with the Task entities in the database.
 * By extending CrudRepository, TaskRepository inherits several methods for working with Task persistence, including methods for saving, deleting, and finding Task entities.
 */
public interface TaskRepository extends CrudRepository<Task, Long> {
}
