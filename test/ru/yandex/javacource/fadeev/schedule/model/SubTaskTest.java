package ru.yandex.javacource.fadeev.schedule.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTaskTest {

    private static SubTask subtask;
    @BeforeEach
    void setUp() {
        subtask = new SubTask("Subtask", "Description", Status.NEW, 999);
    }
    @Test
    void getEpicId() {
        assertEquals(999, subtask.getEpicId());
    }

    @Test
    void setEpicId() {
        subtask.setEpicId(1);
        assertEquals(1, subtask.getEpicId());
    }
}