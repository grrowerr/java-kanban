package ru.yandex.javacource.fadeev.schedule.model;
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

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        if (this.getId() == epicId){
            return;
        }
        this.epicId = epicId;
    }
}
