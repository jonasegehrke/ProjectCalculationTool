package gruppe6.eksamensprojekt;

import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.service.ProjectService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjectServiceTests {

    @Test
    void testRenderProjectList() {
        //Arrange
            ProjectService projectService = new ProjectService();
        //Act
            ArrayList result = new ArrayList();
        //Assert
            assertEquals(result, projectService.renderProjectList());
    }

    @Test
    void testCreateProject() {
        //Arrange
        ProjectService projectService = new ProjectService();
        Project projectX = new Project("test", 0);
        //Act
        Project projectY = projectService.readProject(projectX.getId());
        //Assert
        assertEquals(projectX, projectY);
    }


    @Test
    void testReadProject() {

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
