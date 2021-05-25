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

    public Project readProject(int projectId) {
        projectList = projectMapper.readAllProjects();
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getId() == projectId) {
                currentProjectId = projectList.get(i).getId();
                return projectList.get(i);
            }
        }
        return null;
    }

    public int getCurrentProjectId() {
        return currentProjectId;
    }

    public void deleteProject(int projectId, EmployeeService employeeService) throws Exception {

        //Loop igennem currentTaskList for at få fat i Tasks
        for(int i = 0; i < currentTaskList.size(); i++){
            // Tjekker om taskens projectId er det samme som det Project vi vil slette
            if(currentTaskList.get(i).getProjectId() == projectId){
                //Loop igennem currentSubtaskList for at få fat i Subtasks
                for(int j = 0; j < currentSubtaskList.size(); j++){
                    // Tjekker om subtaskens taskId er det samme som det Task vi vil slette
                    if(currentSubtaskList.get(j).getTaskId() == currentTaskList.get(i).getId()){
                        //Fjern de rigtige timer fra employee
                        employeeService.deductHoursFromEmployee(getEmployeeIdFromSubtask(currentSubtaskList.get(j).getId()), currentSubtaskList.get(j).getHours());
                    }
                }
            }
        }


        readTaskList(projectId);    // Opdater tasklisten til projektId
        ArrayList taskIdList = new ArrayList();     // Initialiser en arrayliste til at holde på taskId

        // Loop igennem tasklist for at få fat i Tasks
        for(int i = 0; i < taskList.size(); i++){
            // Tjekker om taskens projectId er det samme som det Project vi vil slette
            if(taskList.get(i).getProjectId() == projectId){
                // Tilføj taskId til arraylisten
                taskIdList.add(taskList.get(i).getId());
            }
        }

        //Loop igennem projectlist for at få fat i projects
        for(int i = 0; i < projectList.size(); i++){
            // Tjekker om projectId er det samme som det project vi vil slette
            if(projectList.get(i).getId() == projectId){
                // Kald projectMapper for at slette project, task og subtasks i SQL databasen
                projectMapper.deleteProject(projectList.get(i),taskIdList);
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

    public ArrayList readTaskList(int projectId) {

        taskList = taskMapper.readAllTasks();
        currentTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getProjectId() == projectId) {
                currentTaskList.add(taskList.get(i));
                projectHours += taskList.get(i).getHours();
            }
        }


        projectMapper.updateProject(projectId, projectHours);
        projectHours = 0;

        return currentTaskList;
    }

    public void deleteTask(int taskId, EmployeeService employeeService) throws Exception {
        for(int i = 0; i < currentTaskList.size(); i++){
            if(currentTaskList.get(i).getId() == taskId){
                taskMapper.deleteTask(currentTaskList.get(i));

                for(int j = 0; j < currentSubtaskList.size(); j++){
                    if(currentSubtaskList.get(j).getTaskId() == taskId){
                        employeeService.deductHoursFromEmployee(getEmployeeIdFromSubtask(currentSubtaskList.get(j).getId()), currentSubtaskList.get(j).getHours());
                    }
                }

            }
        }
    }






    // Subtask
    public void createSubtask(String title, String hours, int currentTaskId) {
        if (title.trim().length() > 0 && !hours.isEmpty()) {
            Subtask subtask = new Subtask(title, Double.parseDouble(hours), currentTaskId);
            subtaskMapper.createSubtask(subtask);
        }
    }

    public ArrayList readSubtaskList(ArrayList<Task> currentTaskList) {
        subtaskList = subtaskMapper.readAllSubtasks();
        currentSubtaskList = new ArrayList<>();

        for (int i = 0; i < currentTaskList.size(); i++) {
            for(int j = 0; j < subtaskList.size(); j++){
                if (currentTaskList.get(i).getId()== subtaskList.get(j).getTaskId()){
                    currentSubtaskList.add(subtaskList.get(j));
                    taskHours += subtaskList.get(j).getHours();
                }
            }
            currentTaskList.get(i).setHours(taskHours);
            taskMapper.updateTask(currentTaskList.get(i));
            taskHours = 0;
        }


        return currentSubtaskList;
    }

    public void deleteSubtask(int subtaskId){
        for(int i = 0; i < currentSubtaskList.size(); i++){
            if(currentSubtaskList.get(i).getId() == subtaskId){
                subtaskMapper.deleteSubtask(currentSubtaskList.get(i));
            }
        }
    }

    public void assignEmployeeToSubtask(int subtaskId, int employeeId){
        for(int i = 0; i < currentSubtaskList.size(); i++){
            if(currentSubtaskList.get(i).getId() == subtaskId){
                subtaskMapper.assignEmployeeToSubtask(currentSubtaskList.get(i), employeeId);
            }
        }
    }

    public int getEmployeeIdFromSubtask(int subtaskId) throws Exception {
        for(int i = 0; i < subtaskList.size(); i++){
            if(subtaskList.get(i).getId() == subtaskId){
                return subtaskList.get(i).getEmployeeId();
            }
        }
        throw new Exception();
    }

    public double getSubtaskHours(int subtaskId) throws Exception {
        for(int i = 0; i < subtaskList.size(); i++){
            if(subtaskList.get(i).getId() == subtaskId){
                return subtaskList.get(i).getHours();
            }
        }
        throw new Exception();
    }



}
