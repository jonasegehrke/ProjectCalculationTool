package gruppe6.eksamensprojekt.applicationlayer.controllers;

import gruppe6.eksamensprojekt.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;


@Controller
public class FrontController {

    ProjectService projectService = new ProjectService();

    @GetMapping(value = "/")
    public String dashboard(Model model) {
        model.addAttribute("projectlist", projectService.renderProjectList());
        return "dashboard.html";
    }

    @GetMapping(value = "/project")
    public String readProject(@RequestParam("id") int id, Model model) {
        model.addAttribute("projectlist", projectService.renderProjectList());
        model.addAttribute("subtasklist", projectService.readSubtaskList(projectService.readTaskList(id)));
        model.addAttribute("tasklist", projectService.readTaskList(id));
        model.addAttribute("project", projectService.readProject(id));
        return "project.html";
    }

    @GetMapping(value = "/updated-dashboard")
    public String dashboardWithoutSplash(Model model) {
        model.addAttribute("projectlist", projectService.renderProjectList());
        return "dashboard-without-splash.html";
    }

    @PostMapping(value = "/create-project")
    public String createProject(WebRequest request) {
        String title = request.getParameter("project-title");
        projectService.createProject(title);
        return "redirect:/project?id=" + projectService.getCurrentProjectId();
    }

    @PostMapping(value = "/create-task")
    public String createTask(WebRequest request) {
        String title = request.getParameter("task-title");
        projectService.createTask(title, projectService.getCurrentProjectId());
        return "redirect:/project?id=" + projectService.getCurrentProjectId();
    }

    @PostMapping(value = "/create-subtask")
    public String createSubtask(@RequestParam("id") int id, WebRequest request) {
        String title = request.getParameter("subtask-title");
        double hours = Double.parseDouble(request.getParameter("subtask-hours"));
        projectService.createSubtask(title,hours,id);
        return "redirect:/project?id=" + projectService.getCurrentProjectId();
    }

    @PostMapping(value ="/delete-subtask")
    public String deleteSubtask(@RequestParam("id") int id){
        projectService.deleteSubtask(id);
        return "redirect:/project?id=" + projectService.getCurrentProjectId();
    }

    @PostMapping(value = "/delete-task")
    public String deleteTask(@RequestParam("id") int id){
        projectService.deleteTask(id);
        return "redirect:/project?id=" + projectService.getCurrentProjectId();
    }

    @PostMapping(value = "/delete-project")
    public String deleteProject(@RequestParam("id") int id){
        projectService.deleteProject(id);
        return "redirect:/updated-dashboard";
    }
}
