package ru.yandex.javacource.fadeev.schedule.task;
public class SubTask extends Task {

    private int epicId;

    public SubTask(String title, String description, Status status, int epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public SubTask(String title, String description, int id,  Status status) {
        super(title, description, id, status);
    }

    public SubTask(String title, String description, int id, Status status, int epicId) {
        super(title, description,id, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
