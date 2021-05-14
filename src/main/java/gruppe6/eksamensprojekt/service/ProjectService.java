package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.ProjectMapper;
import gruppe6.eksamensprojekt.data.SubtaskMapper;
import gruppe6.eksamensprojekt.data.TaskMapper;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.Subtask;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.util.ArrayList;

public class ProjectService {

    // Project
    ArrayList<Project> projectList;
    ProjectMapper projectMapper = new ProjectMapper();
    private int currentProjectId;

    // Task
    TaskMapper taskMapper = new TaskMapper();
    ArrayList<Task> taskList;
    ArrayList<Task> currentTaskList;
    double projectHours;

    // Subtask
    SubtaskMapper subtaskMapper = new SubtaskMapper();
    ArrayList<Subtask> subtaskList;
    ArrayList<Subtask> currentSubtaskList;
    double taskHours;


    //Project
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

    public int getCurrentProjectId() {
        return currentProjectId;
    }

    public void deleteProject(int id){

        int taskId = 0;

        findTaskList(id);


        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getProjectId() == id){
                taskId = taskList.get(i).getId();
            }
        }

        for(int i = 0; i < projectList.size(); i++){
            if(projectList.get(i).getId() == id){
                projectMapper.deleteProject(projectList.get(i),taskId);
            }
        }
    }


    //Task
    public void createTask(String title, int currentProjectId) {
        if (title.trim().length() > 0) {
            Task task = new Task(title, 0, currentProjectId);
            taskMapper.createTask(task);
        }
    }

    public ArrayList findTaskList(int id) {

        taskList = taskMapper.readAllTasks();
        currentTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getProjectId() == id) {
                currentTaskList.add(taskList.get(i));
                projectHours += taskList.get(i).getHours();
            }
        }


        projectMapper.updateProject(id, projectHours);
        projectHours = 0;

        return currentTaskList;
    }

    public void deleteTask(int id){
        for(int i = 0; i < currentTaskList.size(); i++){
            if(currentTaskList.get(i).getId() == id){
                taskMapper.deleteTask(currentTaskList.get(i));
            }
        }
    }

    // Subtask
    public void createSubtask(String title, double hours, int currentTaskId) {
        if (title.trim().length() > 0 || hours > 0) {
            Subtask subtask = new Subtask(title, hours, currentTaskId);
            subtaskMapper.createSubtask(subtask);
        }
    }

    public ArrayList findSubtaskList(ArrayList<Task> currentProjectTaskList) {
        subtaskList = subtaskMapper.readAllSubtasks();
        currentSubtaskList = new ArrayList<>();

        for (int i = 0; i < currentProjectTaskList.size(); i++) {
            for(int j = 0; j < subtaskList.size(); j++){
                if (currentProjectTaskList.get(i).getId()== subtaskList.get(j).getTaskId()){
                    currentSubtaskList.add(subtaskList.get(j));
                    taskHours += subtaskList.get(j).getHours();
                }
            }
            currentProjectTaskList.get(i).setHours(taskHours);
            taskMapper.updateTask(currentProjectTaskList.get(i));
            taskHours = 0;
        }


        return currentSubtaskList;
    }

    public void deleteSubtask(int id){
        for(int i = 0; i < currentSubtaskList.size(); i++){
            if(currentSubtaskList.get(i).getId() == id){
                subtaskMapper.deleteSubtask(currentSubtaskList.get(i));
            }
        }
    }

}
