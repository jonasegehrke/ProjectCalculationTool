package gruppe6.eksamensprojekt.data;

import gruppe6.eksamensprojekt.domain.model.Project;

import java.sql.*;

public class ProjectMapper {

    public void createProject(Project project){
        try{
            Connection conn = DBManager.getConnection();
            String SQL = "INSERT INTO project (title) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, project.getTitle());

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

}
