package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Project;

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

    public void updateProject(Project project){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "UPDATE project  SET hours =? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ps.setDouble(1,project.getHours());
            ps.setInt(2, project.getId());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public ArrayList<Project> readAllProjects(){
        ArrayList<Project> list = new ArrayList<>();

        try{
            Connection conn = DBManager.getConnection();
            String SQL = "SELECT * FROM project";
            PreparedStatement ps = conn.prepareStatement(SQL);

            ResultSet rs = ps.getGeneratedKeys();

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
