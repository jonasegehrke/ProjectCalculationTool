//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jimmi Paw Pisalita og Jonas Emil Gehrke

package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Subtask;

import java.sql.*;
import java.util.ArrayList;

public class SubtaskMapper {
    /**
     * Creates a subtask in our MySQL database
     * @param subtask an object containing the data needed to create a subtask in our database
     */
    public void createSubtask(Subtask subtask) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO subtask (title, hours, task_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, subtask.getTitle());
            ps.setDouble(2, subtask.getHours());
            ps.setInt(3, subtask.getTaskId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);
            subtask.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Assigns an employee to a specific subtask in our MySQL Database
     * @param subtask an object containing data needed to find the right subtask to assign the employee to
     * @param employeeId Used to sort through the employees assigning the correct one
     */
    public void assignEmployeeToSubtask(Subtask subtask, int employeeId) {
        try {
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE subtask  SET employee_id =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, employeeId);
            ps.setInt(2, subtask.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes a specific subtask from our MySQL Database
     * @param subtask an object containing the data needed to delete the correct subtask from our database
     */
    public void deleteSubtask(Subtask subtask){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "DELETE FROM subtask WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setInt(1, subtask.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Reads all subtask from our database
     * @return returns a list containing all of the subtasks from the database
     */
    public ArrayList<Subtask> readAllSubtasks() {
        ArrayList<Subtask> list = new ArrayList<>();

        try {
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM subtask";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                double hours = rs.getDouble(3);
                int taskId = rs.getInt(4);
                int employeeId = rs.getInt(5);

                Subtask subtask = new Subtask(title, hours, taskId);
                subtask.setId(id);
                subtask.setEmployeeId(employeeId);
                list.add(subtask);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }
}
