package gruppe6.eksamensprojekt;

import gruppe6.eksamensprojekt.domain.model.Employee;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.service.EmployeeService;
import gruppe6.eksamensprojekt.service.ProjectService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

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
