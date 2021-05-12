package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.sql.*;
import java.util.ArrayList;

public class ProjectMapper {

    public void createProject(Project project){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO project (title, hours) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, project.getTitle());
            ps.setDouble(2, project.getHours());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            rs.next();
            int id = rs.getInt(1);
            project.setId(id);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateProject(int id, double hours){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE project  SET hours =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);


            ps.setDouble(1, hours);
            ps.setInt(2, id);

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void deleteProject(Project project, int taskId){
        try{
            Connection conn = DBManager.getConnection();
            System.out.println(taskId);
            System.out.println(project.getId());

            String SQL = "DELETE FROM subtask WHERE task_id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1, taskId);
            ps.executeUpdate();

            String SQL2 = "DELETE FROM task WHERE project_id=?";
            PreparedStatement ps2 = conn.prepareStatement(SQL2);
            ps2.setInt(1, project.getId());
            ps2.executeUpdate();

            String SQL3 = "DELETE FROM project WHERE id=?";
            PreparedStatement ps3 = conn.prepareStatement(SQL3);
            ps3.setInt(1, project.getId());
            ps3.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Project> readAllProjects(){
        ArrayList<Project> list = new ArrayList<>();

        try{
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM project";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                double hours = rs.getDouble(3);
                Project project = new Project(title, hours);
                project.setId(id);
                list.add(project);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return list;
    }

}
