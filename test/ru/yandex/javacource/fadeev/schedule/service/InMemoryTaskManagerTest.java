package ru.yandex.javacource.fadeev.schedule.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.fadeev.schedule.model.Epic;
import ru.yandex.javacource.fadeev.schedule.model.Status;
import ru.yandex.javacource.fadeev.schedule.model.SubTask;
import ru.yandex.javacource.fadeev.schedule.model.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private static TaskManager taskManager;
    private static HistoryManager historyManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager(historyManager);
    }

    @Test
    void getTasks() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));

        assertEquals(2, taskManager.getTasks().size());
    }

    @Test
    void getSubTasks() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));

        assertEquals(2, taskManager.getSubTasks().size());
    }

    @Test
    void getEpics() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        Epic epic2 = taskManager.createEpic(new Epic("Второй эпик", "Описание эпика"));

        assertEquals(2, taskManager.getEpics().size());
    }

    @Test
    void removeTasks() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));

        taskManager.removeTasks();
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    void removeSubTasks() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));

        taskManager.removeSubTasks();

        assertEquals(0, taskManager.getSubTasks().size());
    }

    @Test
    void removeEpics() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        Epic epic2 = taskManager.createEpic(new Epic("Второй эпик", "Описание эпика"));

        taskManager.removeEpics();
        assertEquals(0, taskManager.getEpics().size());
    }

    @Test
    void getTaskById() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));
        int taskId = firstTask.getId();
        assertEquals(firstTask, taskManager.getTaskById(taskId));
    }

    @Test
    void getEpicById() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        Epic epic2 = taskManager.createEpic(new Epic("Второй эпик", "Описание эпика"));

        int epicId = epic1.getId();
        assertEquals(epic1, taskManager.getEpicById(epicId));
    }

    @Test
    void getSubTaskById() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 2, Status.NEW, epic.getId()));

        int subtaskId = firstSubTask.getId();

        assertEquals(firstSubTask, taskManager.getSubTaskById(subtaskId));
    }

    @Test
    void createTask() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        assertEquals(firstTask, taskManager.getTaskById(firstTask.getId()));
    }

    @Test
    void createEpic() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        assertEquals(epic1, taskManager.getEpicById(epic1.getId()));
    }

    @Test
    void createSubTask() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));

        assertEquals(firstSubTask, taskManager.getSubTaskById(firstSubTask.getId()));
    }

    @Test
    void updateTask() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска", 1,
                Status.NEW));
        Task newFirstTask = new Task("Первый Таск Обновленный", "Описание первого Таска обновленного",
                1, Status.NEW);
        taskManager.updateTask(newFirstTask);
        assertEquals(newFirstTask, taskManager.getTaskById(firstTask.getId()));
    }

    @Test
    void updateEpic() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика", 1, Status.NEW));
        Epic newEpic = new Epic("Первый эпик обновленный", "Описание эпика обновленный", 1,
                Status.NEW);
        taskManager.updateEpic(newEpic);
        assertEquals(newEpic, taskManager.getEpicById(epic.getId()));
    }

    @Test
    void updateSubTask() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание первого СабТаска", 1, Status.NEW, epic.getId()));
        SubTask newSubTask = new SubTask("Второй СабТаск",
                "Описание второго СабТаска", 2, Status.NEW, epic.getId());

        taskManager.updateSubTask(newSubTask);
        assertEquals("Второй СабТаск", taskManager.getSubTaskById(newSubTask.getId()).getTitle());
    }

    @Test
    void deleteTaskById() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));

        taskManager.deleteTaskById(firstTask.getId());
        assertEquals(1, taskManager.getTasks().size());
    }

    @Test
    void deleteEpicById() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        Epic epic2 = taskManager.createEpic(new Epic("Второй эпик", "Описание эпика"));

        taskManager.deleteEpicById(epic2.getId());
        assertEquals(1, taskManager.getEpics().size());
    }

    @Test
    void deleteSubTaskById() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 2, Status.NEW, epic.getId()));

        taskManager.deleteSubTaskById(firstSubTask.getId());
        assertEquals(1, taskManager.getSubTasks().size());
    }

    @Test
    void returnEpicSubTasks() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 2, Status.NEW, epic.getId()));

        assertEquals(2, taskManager.returnEpicSubTasks(epic).size());
    }

    @Test
    void getHistory() {
        Epic epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));

        SubTask firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW, epic.getId()));
        SubTask secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 2, Status.NEW, epic.getId()));

        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска",
                Status.NEW));

        taskManager.getEpicById(epic.getId());
        taskManager.getTaskById(firstTask.getId());
        taskManager.getSubTaskById(firstSubTask.getId());
        assertEquals(3, taskManager.getHistory().size());
    }

    @Test
    void twoTasksEqualsIfTheirIdsEquals() {
        Task firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска", 1,
                Status.NEW));
        Task secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска", 1,
                Status.NEW));

        assertEquals(taskManager.getTaskById(1), taskManager.getTaskById(1));
    }

    @Test
    void taskInheritorsEqualsIfTheirIdsEquals() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        epic1.setId(1);
        Epic epic2 = taskManager.createEpic(new Epic("Второй эпик", "Описание эпика"));
        epic2.setId(1);

        assertEquals(taskManager.getEpicById(epic1.getId()), taskManager.getEpicById(epic2.getId()));
    }

    @Test
    void epicCannotBeASubtaskForHimself() {
        Epic epic1 = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика"));
        epic1.addSubTaskId(epic1.getId());
        assertEquals(0, epic1.getSubTasksIds().size());
    }

    @Test
    void subtaskCannotBeAEpicForHimself() {
        SubTask firstSubTask = new SubTask("Первый СабТаск",
                "Описание СабТаска", 1, Status.NEW);
        firstSubTask.setEpicId(firstSubTask.getId());
        assertEquals(0, firstSubTask.getEpicId());
    }

}