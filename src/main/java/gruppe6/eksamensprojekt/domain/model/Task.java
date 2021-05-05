package gruppe6.eksamensprojekt.domain.model;

public class Task {

    private int id;
    private String title;
    private double hours;
    private int projectId;

    public Task(String title, int projectId) {
        this.title = title;
        this.projectId = projectId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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
