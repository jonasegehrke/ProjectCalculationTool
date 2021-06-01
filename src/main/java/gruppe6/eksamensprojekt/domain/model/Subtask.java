//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita

package gruppe6.eksamensprojekt.domain.model;

public class Subtask {

    private int id;
    private String title;
    private double hours = 0;
    private int taskId;
    private int employeeId = 0;

    public Subtask(String title, double hours, int taskId) {
        this.title = title;
        this.hours = hours;
        this.taskId = taskId;
    }

    /**
     * Gets a subtasks id
     * @return returns a subtasks id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a subtasks id
     * @param id is used to set a subtasks id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets a tasks id
     * @return returns a tasks id
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Sets a tasks id
     * @param taskId is used to set a tasks id
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Gets a subtasks title
     * @return returns a subtasks title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a subtasks title
     * @param title is used to set a subtasks title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets a subtasks hours
     * @return returns a subtasks hours
     */
    public double getHours() {
        return hours;
    }

    /**
     * Sets a subtasks amount of hours
     * @param hours is used to set a subtasks amount of hours
     */
    public void setHours(double hours) {
        this.hours = hours;
    }

    /**
     * Gets an employees id
     * @return returns an employees id
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets an employees id
     * @param employeeId is used to set an employees id
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
