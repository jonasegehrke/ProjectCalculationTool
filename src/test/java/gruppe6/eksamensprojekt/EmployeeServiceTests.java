package gruppe6.eksamensprojekt;

import gruppe6.eksamensprojekt.domain.model.Employee;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.service.EmployeeService;
import gruppe6.eksamensprojekt.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTests {


    @Test
    void testCreateEmployee() {
        //Arrange
        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        //Act
        employeeService.createEmployee("testName","testJobTitle");
        employee = (Employee) employeeService.readEmployeeList().get(employeeService.readEmployeeList().size() - 1);

        //Assert
        assertEquals("testName", employee.getEmpName());
    }

    @Test
    void testReadEmployeeList() {
        //Arrange
        EmployeeService employeeService = new EmployeeService();
        ArrayList<Employee> employeeList;
        //Act
        employeeService.createEmployee("testName","testJobTitle");
        employeeList = employeeService.readEmployeeList();

        //Assert


    }


}
