package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.SubTask;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.sql.*;
import java.util.ArrayList;

public class TaskMapper {

    public void createTask(Task task){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO task (title, hours, project_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, task.getTitle());
            ps.setDouble(2, task.getHours());
            ps.setInt(3, task.getProjectId());

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

    public void deleteTask(Task task){
        try{
            Connection conn = DBManager.getConnection();

            String SQL = "DELETE FROM subtask WHERE task_id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,task.getId());
            ps.executeUpdate();

            String SQL2 = "DELETE FROM task WHERE id=?";
            PreparedStatement ps2 = conn.prepareStatement(SQL2);
            ps2.setInt(1, task.getId());
            ps2.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readAllTasks(){
        ArrayList<Task> list = new ArrayList<>();

        try{
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM task";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                double hours = rs.getDouble(3);
                int projectId = rs.getInt(4);
                Task task = new Task(title, hours, projectId);
                task.setId(id);
                list.add(task);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }
}
