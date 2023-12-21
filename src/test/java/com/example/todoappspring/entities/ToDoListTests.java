package com.example.todoappspring.entities;

import com.example.todoappspring.exceptions.TaskAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTests {
    private ToDoList toDoList;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList("Test ToDoList");
    }

    @Test
    public void testGetName() {
        assertEquals("Test ToDoList", toDoList.getName());
    }

    @Test
    public void testGetTasks() {
        assertTrue(toDoList.getTasks().isEmpty());
    }

    @Test
    public void testAddTask() throws Exception {
        Task task = new Task("Test Task", toDoList);
        toDoList.addTask(task);
        assertEquals(1, toDoList.getTasks().size());
        assertEquals(task, toDoList.getTasks().get(0));
    }

    @Test
    public void testAddTaskThrowsException() {
        assertThrows(TaskAlreadyExistsException.class, () -> {
            Task task = new Task("Test Task", toDoList);
            toDoList.addTask(task);
            toDoList.addTask(task); // should throw exception
        });
    }
}