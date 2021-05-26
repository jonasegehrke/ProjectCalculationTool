package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Employee;
import gruppe6.eksamensprojekt.domain.model.Subtask;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeMapper {
    /**
     * Creates an employee in our MySQL database
     * @param employee an object containing the data needed in our database
     */
    public void createEmployee(Employee employee){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO employee (emp_name, job_title) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, employee.getEmpName());
            ps.setString(2, employee.getJobTitle());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);
            employee.setId(id);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads all employes from our MySQL database
     * @return returns an arraylist of all the Employees from our database
     */
    public ArrayList<Employee> readAllEmployees(){

        ArrayList<Employee> list = new ArrayList<>();
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM employee";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()) {

                int id = rs.getInt(1);
                String empName = rs.getString(2);
                String jobTitle = rs.getString(3);
                double plannedHours = rs.getDouble(4);

                Employee employee = new Employee(empName, jobTitle);

                employee.setId(id);
                employee.setPlannedHours(plannedHours);

                list.add(employee);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Edits the amount of hours a specific employee has in our MySQL database
     * @param employee an object of employee containing the data (hours) needed to be edited
     */
    public void editEmployeeHours(Employee employee){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE employee  SET planned_hours =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setDouble(1, employee.getPlannedHours());
            ps.setInt(2, employee.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes an employee from our MySQL Database
     * @param employee an object of the employee used to delete that specific employee from the database
     */
    public void deleteEmployee(Employee employee){
        try{
            Connection conn = DBManager.getConnection();

            String SQL = "UPDATE subtask SET employee_id = null WHERE employee_id =?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, employee.getId());
            ps.executeUpdate();

            String SQL2 = "DELETE FROM employee WHERE id=?";
            PreparedStatement ps2 = conn.prepareStatement(SQL2);
            ps2.setInt(1, employee.getId());
            ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
