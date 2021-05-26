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

    public void deleteProject(Project project, ArrayList taskIdList){
        try{
            Connection conn = DBManager.getConnection();        //Få en connection fra DBManager til SQL
            conn.setAutoCommit(false);          //Slå Auto Commit fra så vi manuelt skal commit alle excecutes

            String SQL = "DELETE FROM subtask WHERE task_id=?";         //SQL statement "Slet alt fra subtask hvor taskId =?"
            PreparedStatement ps = conn.prepareStatement(SQL);          //Prepared statement
            //Loop igennem taskIdList for at slette alle subtasks med dem som task id.
            for (int i = 0; i<taskIdList.size(); i++){
                ps.setInt(1, Integer.parseInt(taskIdList.get(i).toString()));  //Set task id
                ps.executeUpdate();     //Execute
            }

            String SQL2 = "DELETE FROM task WHERE project_id=?";    //SQL statement "Slet alt fra task hvor projectId =?"
            PreparedStatement ps2 = conn.prepareStatement(SQL2);    //Prepared statement
            ps2.setInt(1, project.getId());             //Set projectId
            ps2.executeUpdate();                                    //Execute

            String SQL3 = "DELETE FROM project WHERE id=?";         //SQL statement "Slet alt fra project hvor id =?"
            PreparedStatement ps3 = conn.prepareStatement(SQL3);    //Prepare statement
            ps3.setInt(1, project.getId());             //Set id
            ps3.executeUpdate();                                    //Execute

            conn.commit();                                          //Commit alle executes på samme tid, så ingen information er mistet
            conn.setAutoCommit(true);                               //Slå Auto Commit til

        }catch (SQLException e){                                    //Catch SQLException
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
