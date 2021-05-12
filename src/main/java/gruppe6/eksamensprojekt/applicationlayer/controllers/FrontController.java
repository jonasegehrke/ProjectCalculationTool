package gruppe6.eksamensprojekt.applicationlayer.controllers;

import gruppe6.eksamensprojekt.service.NewService;
import gruppe6.eksamensprojekt.service.ProjectService;
import gruppe6.eksamensprojekt.service.SubTaskService;
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

    NewService newService = new NewService();

    @GetMapping(value = "/")
    public String dashboard(Model model) {
        model.addAttribute("projectlist", newService.renderProjectList());
        return "dashboard.html";
    }

    @GetMapping(value = "/project")
    public String readProject(@RequestParam("id") int id, Model model) {
        model.addAttribute("projectlist", newService.renderProjectList());
        model.addAttribute("subtasklist", newService.findSubTaskList(newService.findTaskList(id)));
        model.addAttribute("tasklist", newService.findTaskList(id));
        model.addAttribute("project", newService.findProject(id));
        return "project.html";
    }

    @GetMapping(value = "/updated-dashboard")
    public String dashboardWithoutSplash(Model model) {
        model.addAttribute("projectlist", newService.renderProjectList());
        return "dashboard-without-splash.html";
    }

    @PostMapping(value = "/create-project")
    public String createProject(WebRequest request) {
        String title = request.getParameter("project-title");
        newService.createProject(title);
        return "redirect:/project?id=" + newService.getCurrentProjectId();
    }

    @PostMapping(value = "/create-task")
    public String createTask(WebRequest request) {
        String title = request.getParameter("task-title");
        newService.createTask(title, newService.getCurrentProjectId());
        return "redirect:/project?id=" + newService.getCurrentProjectId();
    }

    @PostMapping(value = "/create-subtask")
    public String createSubtask(@RequestParam("id") int id, WebRequest request) {
        String title = request.getParameter("subtask-title");
        double hours = Double.parseDouble(request.getParameter("subtask-hours"));
        newService.createSubTask(title,hours,id);
        return "redirect:/project?id=" + newService.getCurrentProjectId();
    }

    @PostMapping(value ="/delete-subtask")
    public String deleteSubtask(@RequestParam("id") int id){
        newService.deleteSubTask(id);
        return "redirect:/project?id=" + newService.getCurrentProjectId();
    }

    @PostMapping(value = "/delete-task")
    public String deleteTask(@RequestParam("id") int id){
        newService.deleteTask(id);
        return "redirect:/project?id=" + newService.getCurrentProjectId();
    }

    @PostMapping(value = "/delete-project")
    public String deleteProject(@RequestParam("id") int id){
        newService.deleteProject(id);
        return "redirect:/updated-dashboard";
    }
}
