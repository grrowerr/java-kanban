package ru.yandex.javacource.fadeev.schedule.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    private static Task task;
    @BeforeEach
    void setUp() {
        task = new Task("Task", "Description", Status.NEW);
    }

    @Test
    void getTitle() {
        assertEquals("Task", task.getTitle());
    }

    @Test
    void setTitle() {
        task.setTitle("New name");
        assertEquals("New name", task.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Description", task.getDescription());
    }

    @Test
    void setDescription() {
        task.setDescription("New description");
        assertEquals("New description", task.getDescription());
    }

    @Test
    void getId() {
        assertEquals(0, task.getId());
    }

    @Test
    void setId() {
        task.setId(22);
        assertEquals(22, task.getId());
    }

    @Test
    void getStatus() {
        assertEquals(Status.NEW, task.getStatus());
    }

    @Test
    void setStatus() {
        task.setStatus(Status.DONE);
        assertEquals(Status.DONE, task.getStatus());
    }
}