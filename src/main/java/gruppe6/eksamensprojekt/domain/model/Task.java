package gruppe6.eksamensprojekt.domain.model;

import java.util.ArrayList;

public class Task {

    private int id;
    private String title;
    private double hours;
    private int projectId;
    private ArrayList<SubTask> subTasks;

    public Task(String title, double hours, int projectId) {
        this.title = title;
        this.hours = hours;
        this.projectId = projectId;
    }

    public void addSubTask(SubTask subTask){
        subTasks.add(subTask);
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
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
