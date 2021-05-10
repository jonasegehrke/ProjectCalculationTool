package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.TaskMapper;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.util.ArrayList;

public class TaskService {
    TaskMapper taskMapper = new TaskMapper();
    ArrayList<Task> taskList;


    public void createTask(String title, int currentProjectId) {
        Task task = new Task(title, 0, currentProjectId);
        taskMapper.createTask(task);
    }

    public ArrayList findTaskList(int id) {
        taskList = taskMapper.readAllTasks();
        ArrayList<Task> currentProjectTaskList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getProjectId() == id) {
                currentProjectTaskList.add(taskList.get(i));
            }
        }
        return currentProjectTaskList;
    }
}
