package com.example.todoappspring.repositories;

import com.example.todoappspring.entities.ToDoList;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/**
 * The ToDoListRepository interface extends CrudRepository, which provides methods for CRUD operations.
 * CRUD stands for Create, Read, Update, and Delete, which are the four basic functions of persistent storage.
 * In the context of this application, the ToDoListRepository interface provides methods to interact with the ToDoList entities in the database.
 * By extending CrudRepository, ToDoListRepository inherits several methods for working with ToDoList persistence, including methods for saving, deleting, and finding ToDoList entities.
 * Additionally, it declares a method to find a ToDoList by its name. This method returns an Optional, which can be used to handle the case where no ToDoList with the given name exists in the database.
 */
public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
    Optional<ToDoList> findByName(final String name);
}
