package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.SubTaskMapper;
import gruppe6.eksamensprojekt.domain.model.SubTask;
import gruppe6.eksamensprojekt.domain.model.Task;

import java.util.ArrayList;

public class SubTaskService {
    SubTaskMapper subTaskMapper = new SubTaskMapper();
    ArrayList<SubTask> subTaskList;

    public void createSubTask(String title, double hours, int currentTaskId) {
        SubTask subTask = new SubTask(title, hours, currentTaskId);
        subTaskMapper.createSubTask(subTask);
    }


    public ArrayList findSubTaskList(ArrayList<Task> currentProjectTaskList) {
        subTaskList = subTaskMapper.readAllSubTasks();
        ArrayList<SubTask> currentSubTaskList= new ArrayList<>();





            for (int i = 0; i < currentProjectTaskList.size(); i++) {
                for(int j = 0; j < subTaskList.size(); j++){
                    if (currentProjectTaskList.get(i).getId()==subTaskList.get(j).getTaskId()){
                        currentSubTaskList.add(subTaskList.get(j));
                    }
                }
            }


        return currentSubTaskList;
    }
}
