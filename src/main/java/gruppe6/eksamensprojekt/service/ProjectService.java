package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.ProjectMapper;
import gruppe6.eksamensprojekt.domain.model.Project;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class ProjectService {
    ArrayList<Project> projectList;
    ProjectMapper projectMapper = new ProjectMapper();
    private int currentProjectId;

    public int getCurrentProjectId() {
        return currentProjectId;
    }


    public ArrayList renderProjectList() {

        return projectList = projectMapper.readAllProjects();
    }

    public void createProject(String title) {
        if (title.trim().length() > 0) {
            Project project = new Project(title, 0);
            projectMapper.createProject(project);
            currentProjectId = project.getId();
        }
    }

    public Project findProject(int id) {
        projectList = projectMapper.readAllProjects();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getId() == id) {
                currentProjectId = projectList.get(i).getId();
                return projectList.get(i);
            }
        }
        return null;
    }
}



