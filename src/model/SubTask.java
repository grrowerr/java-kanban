package model;
//34:18
public class SubTask extends Task {

    private Epic epic;
    public SubTask(String title, String description, Status status) {
        super(title, description, status);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
