package ru.yandex.javacource.fadeev.schedule.model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    private static Epic epic;

    private static ArrayList<Integer> subtaskIds;

    @BeforeEach
    void setUp() {
        subtaskIds = new ArrayList<>();

        epic = new Epic("Epic", "Description", 1, Status.NEW);
        SubTask subtask1 = new SubTask("Subtask", "Description for subtask", Status.NEW, 1);
        SubTask subtask2 = new SubTask("Subtask", "Description for subtask", Status.NEW, 1);
        subtask1.setId(2);
        subtask2.setId(3);

        subtaskIds.add(subtask1.getId());
        subtaskIds.add(subtask2.getId());
        epic.setSubTasksIds(subtaskIds);
    }

    @Test
    void getSubTasksIds() {
        assertEquals(subtaskIds, epic.getSubTasksIds());
    }

    @Test
    void setSubTasksIds() {
        assertEquals(subtaskIds, epic.getSubTasksIds());

    }

    @Test
    void addSubTaskId() {
        SubTask subtask3 = new SubTask("Subtask", "Description for subtask", Status.NEW, 1);
        subtask3.setId(4);

        epic.addSubTaskId(subtask3.getId());

        assertEquals(3, epic.getSubTasksIds().size());
    }

    @Test
    void removeSubTaskId() {
        epic.removeSubTaskId(4);
        assertEquals(2, epic.getSubTasksIds().size());
    }

    @Test
    void deleteSubTasksIds() {
        epic.deleteSubTasksIds();
        assertEquals(0, epic.getSubTasksIds().size());
    }
}