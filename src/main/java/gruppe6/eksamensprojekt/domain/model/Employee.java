//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita
package gruppe6.eksamensprojekt.domain.model;

public class Employee {

    private int id;
    private String empName;
    private String jobTitle;
    private double plannedHours;

    public Employee(String empName, String jobTitle) {
        this.empName = empName;
        this.jobTitle = jobTitle;
    }

    /**
     * Gets employee id
     * @return returns Employee id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets employee id
     * @param id id used to set an Id to the employee
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets employee name
     * @return returns empName
     */

    public String getEmpName() {
        return empName;
    }


    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /**
     * Gets the jobtitle of an employee
     * @return returns a jobTitle of an employee
     */
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Gets the planned hours of an employee
     * @return returns the planned hours of an employee
     */
    public double getPlannedHours() {
        return plannedHours;
    }

    /**
     * Sets the hours of and employee
     * @param plannedHours is used to set the planned hours of an employee
     */
    public void setPlannedHours(double plannedHours) {
        this.plannedHours = plannedHours;
    }

}
