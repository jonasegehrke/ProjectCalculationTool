package gruppe6.eksamensprojekt;

import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.service.ProjectService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EksamensprojektApplicationTests {

    @Test
    void testRenderProjectList() {
        //Arrange
            ProjectService projectService = new ProjectService();
        //Act
            ArrayList result = new ArrayList();
        //Assert
            assertEquals(result, projectService.renderProjectList());
    }
    
}
