package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, SubTask> subTasks;
    private int uniId = 0;

    public TaskManager(HashMap<Integer, Task> tasks, HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subTasks) {
        this.tasks = tasks;
        this.epics = epics;
        this.subTasks = subTasks;
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
        epics = new HashMap<>();
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

    public void createTask(Task task) {
        return;
    }
    public void createEpic(Epic epic) {
        return;
    }

    public void createSubTask(SubTask subTask) {
        return;
    }



}
