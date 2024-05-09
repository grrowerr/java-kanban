package ru.yandex.javacource.fadeev.schedule.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.fadeev.schedule.model.Status;
import ru.yandex.javacource.fadeev.schedule.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {

    private static HistoryManager historyManager;
    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    void add() {
        historyManager.add(new Task("Task", "Description", Status.NEW));
        assertEquals(1, historyManager.getHistory().size());
    }

    @Test
    void getHistory() {
        historyManager.add(new Task("Task1", "Description1", Status.NEW));
        historyManager.add(new Task("Task2", "Description2", Status.NEW));
        assertEquals(2, historyManager.getHistory().size());
    }
}