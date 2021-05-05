package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.SubTask;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.sql.*;

public class SubTaskMapper {

    public void createSubTask(SubTask subTask){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO subtask (title, hours, task_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, subTask.getTitle());
            ps.setDouble(2, subTask.getHours());
            ps.setInt(3, subTask.getTaskId());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);
            subTask.setId(id);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTask(SubTask subTask){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE subtask  SET hours =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setDouble(1,subTask.getHours());
            ps.setInt(2, subTask.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
