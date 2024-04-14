package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

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

    private int generateNewUniId() {
        return ++uniId;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void removeAllTasks() {
        tasks = new HashMap<>();
    }

    public void removeAllSubTasks() {
        for (Epic epic: epics.values()) {
            epic.setSubTasks(new ArrayList<>());
        }
        subTasks = new HashMap<>();
    }

    public void removeAllEpics() {
        for (Epic epic: epics.values()) {
            epic.setSubTasks(new ArrayList<>());
        }
        epics = new HashMap<>();
        subTasks = new HashMap<>();
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
        epic.updateStatus(epic, calculateStatus(epic));
        epics.put(epic.getId(), epic);
        return epic;
    }

    public SubTask createSubTask(SubTask subTask, Epic epic) {
        subTask.setEpic(epic);
        subTask.setId(generateNewUniId());
        epic.addSubTask(subTask);
        epic.updateStatus(epic, calculateStatus(epic));
        epics.put(epic.getId(), epic);
        subTasks.put(subTask.getId(), subTask);
        return subTask;
    }

    public void updateTask(Task task) {
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

    public void updateSubTask(SubTask newSubTask, SubTask oldSubTask) {
        Epic epic = oldSubTask.getEpic();
        newSubTask.setEpic(epic);
        epic.removeSubTask(oldSubTask);
        subTasks.put(newSubTask.getId(), newSubTask);
        epic.addSubTask(newSubTask);
        epic.updateStatus(epic, calculateStatus(epic));
        epics.put(epic.getId(), epic);
    }

    public void deleteTaskById(int uniId) {
        tasks.remove(uniId);
    }

    public void deleteEpicById(int uniId) {
        epics.remove(uniId);
    }

    public void deleteSubTaskById(int uniId) {
        SubTask removedSubTask = subTasks.remove(uniId);
        Epic epic = removedSubTask.getEpic();

        Epic dataEpic = epics.get(epic.getId());
        dataEpic.getSubTasks().remove(removedSubTask);
        epics.put(epic.getId(), dataEpic);
    }

    public ArrayList<SubTask> returnSubTaskList(Epic epic) {
        return epic.getSubTasks();
    }

    public Status calculateStatus(Epic epic) {
        ArrayList<SubTask> epicSubTasks = epic.getSubTasks();

        if (epicSubTasks.isEmpty()) return Status.NEW;
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
            return Status.DONE;
        }
        if (countNew == epicSubTasks.size()) {
            return Status.NEW;
        }
        return Status.IN_PROGRESS;
    }






}
