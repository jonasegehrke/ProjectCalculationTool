package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeMapper {

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

}
