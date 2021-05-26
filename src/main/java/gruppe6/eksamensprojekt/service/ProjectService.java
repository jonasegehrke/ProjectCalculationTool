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

    /**
     * Reads all projects from a list
     * @return returns a new list with all projects
     */
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

    /**
     * Reads projects from a list a returns a single object from that list
     * @param projectId is the ID used to sort through all of the projects
     * @return Returns a project Object from the project list
     */
    public Project readProject(int projectId) {
        projectList = projectMapper.readAllProjects();
        Project result = null;
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getId() == projectId) {
                currentProjectId = projectList.get(i).getId();
                result = projectList.get(i);
            }
        }
        return result;
    }

    /**
     * A getter used to get the field currentProjectId
     * @return returns the field currentProjectId
     */
    public int getCurrentProjectId() {
        return currentProjectId;
    }

    /**
     * Deletes a specific project
     * @param projectId is the ID used to sort through all of the projects
     * @param employeeService is an object from EmployeeService, used to remove the correct amount of hours
     *                        from the employees assigned to the specific project
     * @throws Exception if exception found
     */
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

    /**
     * Creates a task in a project
     * @param title is a String and the title of the task
     * @param currentProjectId is used to add the task to the correct project
     */
    public void createTask(String title, int currentProjectId) {
        if (title.trim().length() > 0) {
            Task task = new Task(title, 0, currentProjectId);
            taskMapper.createTask(task);
        }
    }

    /**
     * Reads all tasks but only adds the ones with the same projectId to a new list of tasks
     * @param projectId is used to sort the tasks into the right projects
     * @return returns a list of all the tasks connected to a specific project
     */
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

    /**
     * Deletes a task
     * @param taskId Used to sort through the tasks, finding the right one
     * @param employeeService is an object from EmployeeService, used to remove the correct amount of hours
     *      *                 from the employees assigned to the specific task
     * @throws Exception if exception found
     */
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

    /**
     * Creates a subtask
     * @param title Title of the subtask
     * @param hours The amount of hours the specific subtask needs
     * @param currentTaskId Used to sort through task, so the subtask is created the correct place
     */
    public void createSubtask(String title, String hours, int currentTaskId) {
        //Tjekker om brugerinput er tom, hvis ikke så bliver subtask oprettet
        if (title.trim().length() > 0 && !hours.isEmpty()) {
            //Create subtask
            Subtask subtask = new Subtask(title, Double.parseDouble(hours), currentTaskId);
            //Send subtask til datalag
            subtaskMapper.createSubtask(subtask);
        }
    }

    /**
     * Reads all the subtasks and returns a list of subtask connected to the same task
     * @param currentTaskList a list of tasks connected to the same project
     * @return returns a list of subtask who are all connected to the same task
     */
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

    /**
     * Delets a subtask
     * @param subtaskId Used to sort through the subtasks finding the correct one to delete
     */
    public void deleteSubtask(int subtaskId){
        for(int i = 0; i < currentSubtaskList.size(); i++){
            if(currentSubtaskList.get(i).getId() == subtaskId){
                subtaskMapper.deleteSubtask(currentSubtaskList.get(i));
            }
        }
    }

    /**
     * Assigns a specific employee to a subtask
     * @param subtaskId Used to sort through the subtasks
     * @param employeeId Used to sort through the employees, finding the one assigned to the specific subtask
     */
    public void assignEmployeeToSubtask(int subtaskId, int employeeId){
        for(int i = 0; i < currentSubtaskList.size(); i++){
            if(currentSubtaskList.get(i).getId() == subtaskId){
                subtaskMapper.assignEmployeeToSubtask(currentSubtaskList.get(i), employeeId);
            }
        }
    }

    /**
     * Gets the employeeId from a specific subtask
     * @param subtaskId Used to sort through subtasks, finding the correct one
     * @return returns the employee id from the specific subtask
     * @throws Exception if exception found
     */
    public int getEmployeeIdFromSubtask(int subtaskId) throws Exception {
        for(int i = 0; i < subtaskList.size(); i++){
            if(subtaskList.get(i).getId() == subtaskId){
                return subtaskList.get(i).getEmployeeId();
            }
        }
        throw new Exception();
    }

    /**
     * Gets the amount of hours from a specific subtask
     * @param subtaskId Used to sort through the subtasks
     * @return returns the amount of hours from a specific subtask
     * @throws Exception if exception found
     */
    public double getSubtaskHours(int subtaskId) throws Exception {
        for(int i = 0; i < subtaskList.size(); i++){
            if(subtaskList.get(i).getId() == subtaskId){
                return subtaskList.get(i).getHours();
            }
        }
        throw new Exception();
    }



}
