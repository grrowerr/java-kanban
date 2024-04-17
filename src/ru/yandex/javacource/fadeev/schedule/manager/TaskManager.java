package ru.yandex.javacource.fadeev.schedule.manager;

import ru.yandex.javacource.fadeev.schedule.task.Epic;
import ru.yandex.javacource.fadeev.schedule.task.Status;
import ru.yandex.javacource.fadeev.schedule.task.SubTask;
import ru.yandex.javacource.fadeev.schedule.task.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;
    private int uniId = 0;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subTasks = new HashMap<>();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeSubTasks() {
        for (Epic epic: epics.values()) {
            epic.deleteSubTasksIds();
            updateEpicStatus(epic.getId());
        }

        subTasks.clear();
    }

    public void removeEpics() {
        epics.clear();
        subTasks.clear();
    }

    public Task getTaskById(int uniId) {
        return tasks.get(uniId);
    }

    public Epic getEpicById(int uniId) {
        return epics.get(uniId);
    }

    public SubTask getSubTaskById(int uniId) {
        return subTasks.get(uniId);
    }

    public Task createTask(Task task) {
        task.setId(generateNewUniId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateNewUniId());
        epics.put(epic.getId(), epic);
        return epic;
    }

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

    public void updateTask(Task task) {
        Task saved = tasks.get(task.getId());
        if (saved == null) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        Epic saved = epics.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setTitle(epic.getTitle());
        saved.setDescription(epic.getDescription());
        epics.put(epic.getId(), saved);
    }

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

    public void deleteTaskById(int uniId) {
        tasks.remove(uniId);
    }

    public void deleteEpicById(int uniId) {
        Epic epic = epics.get(uniId);
        ArrayList<Integer> epicSubTasksIds = epic.getSubTasksIds();
        for(int subId: epicSubTasksIds) {
            subTasks.remove(subId);
        }
        epics.remove(uniId);
    }

    public void deleteSubTaskById(int uniId) {
        SubTask removedSubTask = subTasks.remove(uniId);
        if (removedSubTask == null) {
            return;
        }
        Epic epic = epics.get(removedSubTask.getEpicId());
        epic.removeSubTaskId(uniId);
        updateEpicStatus(epic.getId());
    }

    public ArrayList<SubTask> returnEpicSubTasks(Epic epic) {
        ArrayList<Integer> subTaskIds = returnSubTaskIds(epic);
        ArrayList<SubTask> epicSubTasks = new ArrayList<>();
        for (int subTaskId: subTaskIds) {
            SubTask subTask = subTasks.get(subTaskId);
            epicSubTasks.add(subTask);
        }
        return epicSubTasks;
    }

    private ArrayList<Integer> returnSubTaskIds(Epic epic) {
        return epic.getSubTasksIds();
    }

    private void updateEpicStatus(int epicId) {
        ArrayList<SubTask> epicSubTasks = returnEpicSubTasks(epics.get(epicId));
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
