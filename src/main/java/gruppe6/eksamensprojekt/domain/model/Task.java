//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita
package gruppe6.eksamensprojekt.domain.model;

import java.util.ArrayList;

public class Task {

    private int id;
    private String title;
    private double hours;
    private int projectId;

    public Task(String title, double hours, int projectId) {
        this.title = title;
        this.hours = hours;
        this.projectId = projectId;
    }



    /**
     * Gets a tasks id
     * @return returns a tasks id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a tasks id
     * @param id is used to set a tasks id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets a projects id
     * @return returns a projects id
     */
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets a tasks title
     * @return returns a tasks title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a tasks title
     * @param title is used to set a tasks title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets a tasks total amount of hours
     * @return returns a tasks total amount of hours
     */

    public double getHours() {
        return hours;
    }

    /**
     * Sets a tasks total amount of hours
     * @param hours is used to set a tasks total amount of hours
     */
    public void setHours(double hours) {
        this.hours = hours;
    }

}
