package gruppe6.eksamensprojekt.applicationlayer.controllers;

import gruppe6.eksamensprojekt.data.ProjectMapper;
import gruppe6.eksamensprojekt.data.SubTaskMapper;
import gruppe6.eksamensprojekt.data.TaskMapper;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.SubTask;
import gruppe6.eksamensprojekt.domain.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class FrontController {

    int currentProjectId;

    ProjectMapper projectMapper = new ProjectMapper();
    TaskMapper taskMapper = new TaskMapper();
    SubTaskMapper subTaskMapper = new SubTaskMapper();
    ArrayList<Project> projectList;
    ArrayList<Task> taskList;

    @GetMapping(value = "/")
    public String dashboard(Model model){
        renderProjectList(model);

        return "dashboard.html";
    }

    @PostMapping(value = "/create-project")
    public String createProject(WebRequest request){
        String title = request.getParameter("project-title");
        if(title!=null) {
            Project project = new Project(title, 0);
            projectMapper.createProject(project);
        }

        return "redirect:/";
    }

    @PostMapping(value = "/create-task")
    public String createTask(WebRequest request){
        String title = request.getParameter("task-title");
        Task task = new Task(title, 0, currentProjectId);
        taskMapper.createTask(task);
        return "redirect:/project?id=" + currentProjectId;
    }

    @GetMapping(value = "/project")
    public String project(@RequestParam("id") int id, Model model){
        renderProjectList(model);
        for(int i = 0; i < projectList.size(); i++){
            if(projectList.get(i).getId()==id){
                model.addAttribute("project", projectList.get(i));
                currentProjectId = projectList.get(i).getId();
            }
        }

        taskList = taskMapper.readAllTasks();
        ArrayList<Task> currentProjectTaskList = new ArrayList<>();
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getProjectId()==id){
                currentProjectTaskList.add(taskList.get(i));
            }
        }
        model.addAttribute("tasklist", currentProjectTaskList);
        return "project.html";
    }

    public void renderProjectList(Model model){
        projectList = projectMapper.readAllProjects();
        model.addAttribute("projectlist", projectList);
    }
}
