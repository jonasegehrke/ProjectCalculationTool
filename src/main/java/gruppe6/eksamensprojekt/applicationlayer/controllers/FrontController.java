package gruppe6.eksamensprojekt.applicationlayer.controllers;

import gruppe6.eksamensprojekt.data.ProjectMapper;
import gruppe6.eksamensprojekt.data.SubTaskMapper;
import gruppe6.eksamensprojekt.data.TaskMapper;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.SubTask;
import gruppe6.eksamensprojekt.domain.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    ProjectMapper projectMapper = new ProjectMapper();
    TaskMapper taskMapper = new TaskMapper();
    SubTaskMapper subTaskMapper = new SubTaskMapper();

    Project project;
    Task task;
    SubTask subTask;

    @GetMapping(value = "/")

    public String dashboard(){

        //Create project
        project = new Project("TestObject");
        projectMapper.createProject(project);


        //Create task
        task = new Task("TestObject", 1);
        taskMapper.createTask(task);


        //create subtasks
        double hours = 0;
        for(int i = 0; i < 3; i++){

            subTask = new SubTask("Subtask" + i, 3, 1);
            hours += subTask.getHours();
            subTaskMapper.createSubTask(subTask);
        }

        //update hours
        task.setHours(hours);
        taskMapper.updateTask(task);


        project.setHours(hours);
        projectMapper.updateProject(project);


        return "dashboard.html";
    }
}
