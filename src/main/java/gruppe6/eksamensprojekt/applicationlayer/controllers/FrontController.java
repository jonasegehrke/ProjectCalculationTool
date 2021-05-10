package gruppe6.eksamensprojekt.applicationlayer.controllers;

import gruppe6.eksamensprojekt.service.ProjectService;
import gruppe6.eksamensprojekt.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;


@Controller
public class FrontController {


    ProjectService projectService = new ProjectService();
    TaskService taskService = new TaskService();

    @GetMapping(value = "/")
    public String dashboard(Model model) {
        model.addAttribute("projectlist", projectService.renderProjectList(model));
        return "dashboard.html";
    }

    @PostMapping(value = "/create-project")
    public String createProject(WebRequest request) {
        String title = request.getParameter("project-title");
        projectService.createProject(title);
        return "redirect:/";
    }

    @PostMapping(value = "/create-task")
    public String createTask(WebRequest request) {
        String title = request.getParameter("task-title");
        taskService.createTask(title, projectService.getCurrentProjectId());
        return "redirect:/project?id=" + projectService.getCurrentProjectId();
    }

    @GetMapping(value = "/project")
    public String readProject(@RequestParam("id") int id, Model model) {
        model.addAttribute("projectlist", projectService.renderProjectList(model));
        model.addAttribute("project", projectService.findProject(id));
        model.addAttribute("tasklist", taskService.findTaskList(id));
        return "project.html";
    }


}
