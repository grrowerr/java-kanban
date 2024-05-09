package ru.yandex.javacource.fadeev.schedule.model;

import java.util.ArrayList;
public class Epic extends Task {

    private ArrayList<Integer> subTasksIds = new ArrayList<>();

    public Epic (String title, String description, int id, Status status) {
        super(title, description, id, status);
    }

    public Epic(String title, String description) {
        super(title, description, Status.NEW);
    }

    public ArrayList<Integer> getSubTasksIds() {
        return subTasksIds;
    }

    public void setSubTasksIds(ArrayList<Integer> subTasksIds) {
        this.subTasksIds = subTasksIds;
    }

    public void addSubTaskId(int subTaskId) {
        if (this.getId() == subTaskId) {
            return;
        }
        subTasksIds.add(subTaskId);
    }

    public void removeSubTaskId(Integer subTaskId) {
        subTasksIds.remove(subTaskId);
    }

    public void deleteSubTasksIds() {
        subTasksIds.clear();
    }

}
