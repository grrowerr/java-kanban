/*
* Вынес повторяющиеся задачи в поля класса
* И инициализирую их в методе setUp
*/
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
    private static Task firstTask;
    private static Task secondTask;

    private static Epic epic;
    private static Epic epic2;
    private static SubTask firstSubTask;
    private static SubTask secondSubTask;



    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager(historyManager);

        epic = taskManager.createEpic(new Epic("Первый эпик", "Описание эпика", 1, Status.NEW));

        epic2 = taskManager.createEpic(new Epic("Второй эпик", "Описание эпика", 2, Status.NEW));


        firstSubTask = taskManager.createSubTask(new SubTask("Первый СабТаск",
                "Описание СабТаска", 3, Status.NEW, epic.getId()));
        secondSubTask = taskManager.createSubTask(new SubTask("Второй СабТаск",
                "Описание СабТаска", 4, Status.NEW, epic.getId()));

        firstTask = taskManager.createTask(new Task("Первый Таск", "Описание первого Таска",5,
                Status.NEW));
        secondTask = taskManager.createTask(new Task("Второй Таск", "Описание второго Таска", 6,
                Status.NEW));
    }

    @Test
    void getTasks() {
        assertEquals(2, taskManager.getTasks().size());
    }

    @Test
    void getSubTasks() {
        assertEquals(2, taskManager.getSubTasks().size());
    }

    @Test
    void getEpics() {
        assertEquals(2, taskManager.getEpics().size());
    }

    @Test
    void removeTasks() {
        taskManager.removeTasks();
        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    void removeSubTasks() {
        taskManager.removeSubTasks();
        assertEquals(0, taskManager.getSubTasks().size());
    }

    @Test
    void removeEpics() {
        taskManager.removeEpics();
        assertEquals(0, taskManager.getEpics().size());
    }

    @Test
    void getTaskById() {
        int taskId = firstTask.getId();
        assertEquals(firstTask, taskManager.getTaskById(taskId));
    }

    @Test
    void getEpicById() {
        int epicId = epic.getId();
        assertEquals(epic, taskManager.getEpicById(epicId));
    }

    @Test
    void getSubTaskById() {
        int subtaskId = firstSubTask.getId();
        assertEquals(firstSubTask, taskManager.getSubTaskById(subtaskId));
    }

    @Test
    void createTask() {
        assertEquals(firstTask, taskManager.getTaskById(firstTask.getId()));
    }

    @Test
    void createEpic() {
        assertEquals(epic, taskManager.getEpicById(epic.getId()));
    }

    @Test
    void createSubTask() {
        assertEquals(firstSubTask, taskManager.getSubTaskById(firstSubTask.getId()));
    }

    @Test
    void updateTask() {
        Task newFirstTask = new Task("Первый Таск Обновленный", "Описание первого Таска обновленного",
                5, Status.NEW);
        taskManager.updateTask(newFirstTask);
        assertEquals(newFirstTask, taskManager.getTaskById(firstTask.getId()));
    }

    @Test
    void updateEpic() {
        Epic newEpic = new Epic("Первый эпик обновленный", "Описание эпика обновленный", 1,
                Status.NEW);
        taskManager.updateEpic(newEpic);
        assertEquals(newEpic, taskManager.getEpicById(epic.getId()));
    }

    @Test
    void updateSubTask() {
        SubTask newSubTask = new SubTask("Новый СабТаск",
                "Описание нового СабТаска", 3, Status.NEW, epic.getId());

        taskManager.updateSubTask(newSubTask);
        assertEquals("Новый СабТаск", taskManager.getSubTaskById(newSubTask.getId()).getTitle());
    }

    @Test
    void deleteTaskById() {
        taskManager.deleteTaskById(firstTask.getId());
        assertEquals(1, taskManager.getTasks().size());
    }

    @Test
    void deleteEpicById() {
        taskManager.deleteEpicById(epic2.getId());
        assertEquals(1, taskManager.getEpics().size());
    }

    @Test
    void deleteSubTaskById() {
        taskManager.deleteSubTaskById(firstSubTask.getId());
        assertEquals(1, taskManager.getSubTasks().size());
    }

    @Test
    void returnEpicSubTasks() {
        assertEquals(2, taskManager.returnEpicSubTasks(epic).size());
    }

    @Test
    void getHistory() {
        taskManager.getEpicById(epic.getId());
        taskManager.getTaskById(firstTask.getId());
        taskManager.getSubTaskById(firstSubTask.getId());
        assertEquals(3, taskManager.getHistory().size());
    }

    @Test
    void twoTasksEqualsIfTheirIdsEquals() {
        secondTask.setId(1);
        assertEquals(taskManager.getTaskById(1), taskManager.getTaskById(1));
    }

    @Test
    void taskInheritorsEqualsIfTheirIdsEquals() {
    epic.setId(99);
    epic2.setId(99);
        assertEquals(taskManager.getEpicById(epic.getId()), taskManager.getEpicById(epic2.getId()));
    }

    @Test
    void epicCannotBeASubtaskForHimself() {
        epic.addSubTaskId(epic.getId());
        assertEquals(2, epic.getSubTasksIds().size());
    }

    @Test
    void subtaskCannotBeAEpicForHimself() {
        firstSubTask.setEpicId(firstSubTask.getId());
        assertEquals(1, firstSubTask.getEpicId());
    }

}