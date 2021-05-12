package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.ProjectMapper;
import gruppe6.eksamensprojekt.data.SubTaskMapper;
import gruppe6.eksamensprojekt.data.TaskMapper;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.SubTask;
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
    ArrayList<Task> currentProjectTaskList;
    double projectHours;

    // Subtask
    SubTaskMapper subTaskMapper = new SubTaskMapper();
    ArrayList<SubTask> subTaskList;
    ArrayList<SubTask> currentSubTaskList;
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
        currentProjectTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getProjectId() == id) {
                currentProjectTaskList.add(taskList.get(i));
                projectHours += taskList.get(i).getHours();
            }
        }


        projectMapper.updateProject(id, projectHours);
        projectHours = 0;

        return currentProjectTaskList;
    }

    public void deleteTask(int id){
        for(int i = 0; i < currentProjectTaskList.size(); i++){
            if(currentProjectTaskList.get(i).getId() == id){
                taskMapper.deleteTask(currentProjectTaskList.get(i));
            }
        }
    }

    public ArrayList<Task> getCurrentProjectTaskList() {
        return currentProjectTaskList;
    }

    // Subtask
    public void createSubTask(String title, double hours, int currentTaskId) {
        if (title.trim().length() > 0 || hours > 0) {
            SubTask subTask = new SubTask(title, hours, currentTaskId);
            subTaskMapper.createSubTask(subTask);
        }
    }

    public ArrayList findSubTaskList(ArrayList<Task> currentProjectTaskList) {
        subTaskList = subTaskMapper.readAllSubTasks();
        currentSubTaskList = new ArrayList<>();

        for (int i = 0; i < currentProjectTaskList.size(); i++) {
            for(int j = 0; j < subTaskList.size(); j++){
                if (currentProjectTaskList.get(i).getId()==subTaskList.get(j).getTaskId()){
                    currentSubTaskList.add(subTaskList.get(j));
                    taskHours += subTaskList.get(j).getHours();
                }
            }
            currentProjectTaskList.get(i).setHours(taskHours);
            taskMapper.updateTask(currentProjectTaskList.get(i));
            taskHours = 0;
        }


        return currentSubTaskList;
    }

    public void deleteSubTask(int id){
        for(int i = 0; i < currentSubTaskList.size(); i++){
            if(currentSubTaskList.get(i).getId() == id){
                subTaskMapper.deleteSubTask(currentSubTaskList.get(i));
            }
        }
    }

}
