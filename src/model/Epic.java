package model;

import java.util.ArrayList;
//43:00
public class Epic extends Task {

    private ArrayList<SubTask> subTasks = new ArrayList<>();

    public Epic(String title, String description, Status status) {
        super(title, description, status);
    }

    public Epic(String title, String description) {
        super(title, description, Status.NEW);
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
    }

    public void updateStatus(Epic epic, Status newStatus) {
         epic.setStatus(newStatus);
    }

}
