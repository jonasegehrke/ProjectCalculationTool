package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.TaskMapper;
import gruppe6.eksamensprojekt.domain.model.SubTask;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.util.ArrayList;

public class TaskService {
    TaskMapper taskMapper = new TaskMapper();
    ArrayList<Task> taskList;
    ArrayList<Task> currentProjectTaskList;
    private int currentTaskId;

    public int getCurrentTaskId() {
        return currentTaskId;
    }

    public void createTask(String title, int currentProjectId) {
        Task task = new Task(title, 0, currentProjectId);
        taskMapper.createTask(task);
    }

    public ArrayList findTaskList(int id) {

        taskList = taskMapper.readAllTasks();
        currentProjectTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getProjectId() == id) {
                currentProjectTaskList.add(taskList.get(i));
            }
        }


        return currentProjectTaskList;
    }

    public void addSubTaskToTaskList(ArrayList<SubTask> subTaskList){

        for(int i = 0; i < currentProjectTaskList.size(); i++){
            for(int j = 0; j < subTaskList.size(); j++){
                if(currentProjectTaskList.get(i).getId() == subTaskList.get(i).getTaskId()){
                    currentProjectTaskList.get(i).addSubTask(subTaskList.get(i));
                }
            }
        }

    }

}
