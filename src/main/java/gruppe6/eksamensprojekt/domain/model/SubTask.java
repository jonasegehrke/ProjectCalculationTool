package gruppe6.eksamensprojekt.domain.model;

public class SubTask {

    private int id;
    private String title;
    private double hours;
    private int taskId;

    public SubTask(String title, double hours, int taskId) {
        this.title = title;
        this.hours = hours;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

}
