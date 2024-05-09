package ru.yandex.javacource.fadeev.schedule.service;

import ru.yandex.javacource.fadeev.schedule.model.Epic;
import ru.yandex.javacource.fadeev.schedule.model.SubTask;
import ru.yandex.javacource.fadeev.schedule.model.Task;
import java.util.List;
public interface TaskManager {
    List<Task> getTasks();

    List<SubTask> getSubTasks();

    List<Epic> getEpics();

    void removeTasks();

    void removeSubTasks();

    void removeEpics();

    Task getTaskById(int uniId);

    Epic getEpicById(int uniId);

    SubTask getSubTaskById(int uniId);

    Task createTask(Task task);

    Epic createEpic(Epic epic);

    SubTask createSubTask(SubTask subTask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    void deleteTaskById(int uniId);

    void deleteEpicById(int uniId);

    void deleteSubTaskById(int uniId);

    List<SubTask> returnEpicSubTasks(Epic epic);

    List<Task> getHistory();


}
