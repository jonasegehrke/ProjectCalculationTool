package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.sql.*;

public class TaskMapper {

    public void createTask(Task task){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO task (title, project_id) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, task.getTitle());
            ps.setInt(2, task.getProjectId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);
            task.setId(id);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTask(Task task){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE task  SET hours =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setDouble(1,task.getHours());
            ps.setInt(2, task.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
