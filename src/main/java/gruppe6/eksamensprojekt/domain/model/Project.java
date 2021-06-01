//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita
package gruppe6.eksamensprojekt.domain.model;


public class Project {

    private int id;
    private String title;
    private double hours;

    public Project(String title, double hours) {
        this.hours = hours;
        this.title = title;
    }

    /**
     * Gets project id
     * @return returns a projects id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a projects id
     * @param id used to set a projects id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets a projects title
     * @return returns a projects title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a projects title
     * @param title is used to set a projects title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets a projects total amount of hours
     * @return returns a projects total amount of hours
     */
    public double getHours() {
        return hours;
    }

    /**
     * Sets a projects total amount of hours
     * @param hours is used to set a projects total amount of hours
     */
    public void setHours(double hours) {
        this.hours = hours;
    }

}
