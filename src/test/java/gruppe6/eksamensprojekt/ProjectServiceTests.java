//Peer programming: Andreas Holm Andersen, Jonas Emil Gehrke og Jimmi Paw Pisalita

package gruppe6.eksamensprojekt;

import gruppe6.eksamensprojekt.domain.model.Employee;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.domain.model.Subtask;
import gruppe6.eksamensprojekt.domain.model.Task;
import gruppe6.eksamensprojekt.service.EmployeeService;
import gruppe6.eksamensprojekt.service.ProjectService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ProjectServiceTests {

    @Test
    void testReadProject() {
        //Arrange
            ProjectService projectService = new ProjectService();
            Project project;
        //Act
            projectService.createProject("test");
            project = projectService.readProject(projectService.getCurrentProjectId());
        //Assert
            assertEquals("test", project.getTitle());
    }

    @Test
    void testCreateProject() {
        //Arrange
            ProjectService projectService = new ProjectService();
            Project project;
        //Act
            projectService.createProject("test");
            project = projectService.readProject(projectService.getCurrentProjectId());
        //Assert
            assertEquals("test", project.getTitle());
    }

    @Test
    void testDeleteProject() throws Exception {
        //Arrange
            ProjectService projectService = new ProjectService();
            EmployeeService employeeService = new EmployeeService();
            Project project;
        //Act
            projectService.createProject("testDeleteProject");
            projectService.readTaskList(projectService.getCurrentProjectId());
            projectService.renderProjectList();
            projectService.deleteProject(projectService.getCurrentProjectId(), employeeService);
            project = projectService.readProject(projectService.getCurrentProjectId());
        //Assert
            assertNull(project);
    }

    @Test
    void testReadTask() {
        //Arrange
            ProjectService projectService = new ProjectService();
            ArrayList<Task> task;
        //Act
            projectService.createProject("testProject");
            projectService.createTask("testTask", projectService.getCurrentProjectId());
            task = projectService.readTaskList(projectService.getCurrentProjectId());
        //Assert
            assertEquals("testTask", task.get(0).getTitle());

    }

    @Test
    void testCreateTask() {
        //Arrange
            ProjectService projectService = new ProjectService();
            ArrayList<Task> task;
        //Act
            projectService.createProject("testProject");
            projectService.createTask("testTask", projectService.getCurrentProjectId());
            task = projectService.readTaskList(projectService.getCurrentProjectId());
        //Assert
            assertEquals("testTask", task.get(0).getTitle());
    }

    @Test
    void testDeleteTask() throws Exception {
        //Arrange
            ProjectService projectService = new ProjectService();
            EmployeeService employeeService = new EmployeeService();
            Task task;
            int index;

        //Act
            projectService.createProject("testProject");
            projectService.createTask("testTask", projectService.getCurrentProjectId());
            projectService.readSubtaskList(projectService.readTaskList(projectService.getCurrentProjectId()));
            
            index = projectService.readTaskList(projectService.getCurrentProjectId()).size()-1;
            task = projectService.readTaskList(projectService.getCurrentProjectId()).get(index);

            projectService.deleteTask(task.getId(), employeeService);
            task = readTaskById(projectService.readTaskList(projectService.getCurrentProjectId()), task.getId());

       //Assert
            assertNull(task);

    }

    @Test
    void testReadSubtask() {
        //Arrange
            ProjectService projectService = new ProjectService();
            ArrayList<Task> taskList;
            ArrayList<Subtask> subtaskList;
        //Act
            projectService.createProject("testProject");
            projectService.createTask("testTask", projectService.getCurrentProjectId());
            taskList = projectService.readTaskList(projectService.getCurrentProjectId());

            projectService.createSubtask("testSubtask", "0", taskList.get(0).getId());
            subtaskList = projectService.readSubtaskList(taskList);

        //Assert
            assertEquals("testSubtask", subtaskList.get(0).getTitle());
    }

    @Test
    void testCreateSubtask() {
        //Arrange
        ProjectService projectService = new ProjectService();
        ArrayList<Task> taskList;
        ArrayList<Subtask> subtaskList;
        //Act
        projectService.createProject("testProject");
        projectService.createTask("testTask", projectService.getCurrentProjectId());
        taskList = projectService.readTaskList(projectService.getCurrentProjectId());

        projectService.createSubtask("testSubtask", "0", taskList.get(0).getId());
        subtaskList = projectService.readSubtaskList(taskList);

        //Assert
        assertEquals("testSubtask", subtaskList.get(0).getTitle());
    }

    @Test
    void testDeleteSubtask() {
        //Arrange
            ProjectService projectService = new ProjectService();
            ArrayList<Task> taskList;
            ArrayList<Subtask> subtaskList;
            int index;
            int subtaskId;
            Subtask subtask;
        //Act
        projectService.createProject("testProject");

        projectService.createTask("testTask", projectService.getCurrentProjectId());
        taskList = projectService.readTaskList(projectService.getCurrentProjectId());

        projectService.createSubtask("testSubtask", "0", taskList.get(0).getId());
        subtaskList = projectService.readSubtaskList(taskList);

        index = projectService.readSubtaskList(taskList).size()-1;
        subtaskId = subtaskList.get(0).getId();

        projectService.deleteSubtask(subtaskList.get(index).getId());

        subtaskList = projectService.readSubtaskList(taskList);
        subtask = readSubtaskById(subtaskList, subtaskId);

        //Assert
        assertNull(subtask);
    }

    @Test
    void testAssignEmployeeToSubtask() {
        //Arrange
        ProjectService projectService = new ProjectService();
        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        Subtask subtask;
        ArrayList<Task> taskList;
        ArrayList<Subtask> subtaskList;
        //Act
        employeeService.createEmployee("testName", "testJobTitle");
        employee = employeeService.readEmployeeList().get(employeeService.readEmployeeList().size() - 1);

        projectService.createProject("testProject");

        projectService.createTask("testTask", projectService.getCurrentProjectId());
        taskList = projectService.readTaskList(projectService.getCurrentProjectId());

        projectService.createSubtask("testSubtask", "0", taskList.get(0).getId());
        subtaskList = projectService.readSubtaskList(taskList);
        subtask = readSubtaskById(subtaskList, subtaskList.get(0).getId());

        projectService.assignEmployeeToSubtask(subtask.getId(), employee.getId());

        subtaskList = projectService.readSubtaskList(taskList);
        subtask = readSubtaskById(subtaskList, subtaskList.get(0).getId());

        //Assert
        assertEquals(employee.getId(), subtask.getEmployeeId());
    }



    Task readTaskById(ArrayList<Task> taskList, int taskId){
        //Set task til null
        Task task = null;

        //Loop igennem taskList
        for(int i = 0; i<taskList.size(); i++){
            //Tjekker om task id er det samme som vi søger efter
            if (taskList.get(i).getId() == taskId){
                //Set task
                task = taskList.get(i);
            }
        }
        return task;
    }

    Subtask readSubtaskById(ArrayList<Subtask> subtaskList, int subtaskId){
        //Set Subtask til null
        Subtask subtask = null;

        //Loop igennem SubtaskList
        for(int i = 0; i<subtaskList.size(); i++){
            //Tjekker om Subtask id er det samme som vi søger efter
            if (subtaskList.get(i).getId() == subtaskId){
                //Set Subtask
                subtask = subtaskList.get(i);
            }
        }
        return subtask;
    }




    /**
     *
     * template for test
     *
     */
    @Test
    void test() {
        //Arrange

        //Act

        //Assert

    }

}
