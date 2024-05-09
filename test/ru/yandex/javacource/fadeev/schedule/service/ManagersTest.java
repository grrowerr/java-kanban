package ru.yandex.javacource.fadeev.schedule.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {


    @Test
    void getDefault() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager);
    }

    @Test
    void getDefaultHistory() {
        HistoryManager HistoryManager = Managers.getDefaultHistory();
        assertNotNull(HistoryManager);
    }
}