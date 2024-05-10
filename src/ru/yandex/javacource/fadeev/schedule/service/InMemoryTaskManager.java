/*
* В 14, 15, 16 строках поменял тип переменных для хранения тасок с HashMap на Map
*
* За совет по поводу метода updateEpic отдельное спасибо!!!
* Я вообще не понимаю, как сам не увидел этой проблемы
*/

package ru.yandex.javacource.fadeev.schedule.service;

import ru.yandex.javacource.fadeev.schedule.model.Epic;
import ru.yandex.javacource.fadeev.schedule.model.Status;
import ru.yandex.javacource.fadeev.schedule.model.SubTask;
import ru.yandex.javacource.fadeev.schedule.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, SubTask> subTasks;
    private int uniId = 0;
    private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void removeTasks() {
        tasks.clear();
    }

    @Override
    public void removeSubTasks() {
        for (Epic epic: epics.values()) {
            epic.deleteSubTasksIds();
            updateEpicStatus(epic.getId());
        }

        subTasks.clear();
    }

    @Override
    public void removeEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public Task getTaskById(int uniId) {
        Task task = tasks.get(uniId);
        historyManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int uniId) {
        Epic epic = epics.get(uniId);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public SubTask getSubTaskById(int uniId) {
        SubTask subTask = subTasks.get(uniId);
        historyManager.add(subTask);
        return subTask;
    }

    @Override
    public Task createTask(Task task) {
        task.setId(generateNewUniId());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic createEpic(Epic epic) {
        epic.setId(generateNewUniId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public SubTask createSubTask(SubTask subTask) {
        int epicId = subTask.getEpicId();
        Epic epic = epics.get(epicId);

        if (epic == null) {
            return null;
        }
        int id = generateNewUniId();
        subTask.setId(id);
        subTasks.put(id, subTask);
        epic.addSubTaskId(id);
        updateEpicStatus(epicId);
        return subTask;
    }

    @Override
    public void updateTask(Task task) {
        Task saved = tasks.get(task.getId());
        if (saved == null) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        final Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        epic.setSubTasksIds(savedEpic.getSubTasksIds());
        epic.setStatus(savedEpic.getStatus());
        epics.put(epic.getId(), epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        int id = subTask.getId();
        int epicId = subTask.getEpicId();
        SubTask savedSubTask = subTasks.get(id);
        if (savedSubTask == null) {
            return;
        }
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        subTasks.put(id, subTask);
        updateEpicStatus(epicId);
    }

    @Override
    public void deleteTaskById(int uniId) {
        tasks.remove(uniId);
    }

    @Override
    public void deleteEpicById(int uniId) {
        Epic epic = epics.get(uniId);
        ArrayList<Integer> epicSubTasksIds = epic.getSubTasksIds();
        for(int subId: epicSubTasksIds) {
            subTasks.remove(subId);
        }
        epics.remove(uniId);
    }

    @Override
    public void deleteSubTaskById(int uniId) {
        SubTask removedSubTask = subTasks.remove(uniId);
        if (removedSubTask == null) {
            return;
        }
        Epic epic = epics.get(removedSubTask.getEpicId());
        epic.removeSubTaskId(uniId);
        updateEpicStatus(epic.getId());
    }

    @Override
    public List<SubTask> returnEpicSubTasks(Epic epic) {
        List<Integer> subTaskIds = returnSubTaskIds(epic);
        ArrayList<SubTask> epicSubTasks = new ArrayList<>();
        for (int subTaskId: subTaskIds) {
            SubTask subTask = subTasks.get(subTaskId);
            epicSubTasks.add(subTask);
        }
        return epicSubTasks;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private List<Integer> returnSubTaskIds(Epic epic) {
        return epic.getSubTasksIds();
    }

    private void updateEpicStatus(int epicId) {
        List<SubTask> epicSubTasks = returnEpicSubTasks(epics.get(epicId));
        Epic epic = epics.get(epicId);
        if (epicSubTasks.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }
        int countNew = 0;
        int countDone = 0;
        for (SubTask subTask : epicSubTasks) {
            if (Status.NEW.equals(subTask.getStatus())){
                countNew++;
            } else if (Status.DONE.equals(subTask.getStatus())) {
                countDone++;
            }
        }
        if (countDone == epicSubTasks.size()) {
            epic.setStatus(Status.DONE);
            return;
        }
        if (countNew == epicSubTasks.size()) {
            epic.setStatus(Status.NEW);
            return;
        }
        epic.setStatus(Status.IN_PROGRESS);
    }

    private int generateNewUniId() {
        return ++uniId;
    }
}
