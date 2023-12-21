package com.example.todoappspring.entities;

import com.example.todoappspring.exceptions.TaskAlreadyDoneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTests {
    private Task task;

    @BeforeEach
    public void setUp() {
        ToDoList todoList = new ToDoList();
        task = new Task("Test Task", todoList);
    }

    @Test
    public void testGetName() {
        assertEquals("Test Task", task.getName());
    }

    @Test
    public void testIsDone() {
        assertFalse(task.isDone());
    }

    @Test
    public void testDone() throws Exception {
        task.done();
        assertTrue(task.isDone());
    }

    @Test
    public void testDoneThrowsException() {
        assertThrows(TaskAlreadyDoneException.class, () -> {
            task.done();
            task.done(); // should throw exception
        });
    }
}