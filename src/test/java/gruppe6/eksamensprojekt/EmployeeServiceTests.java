package gruppe6.eksamensprojekt;

import gruppe6.eksamensprojekt.domain.model.Employee;
import gruppe6.eksamensprojekt.domain.model.Project;
import gruppe6.eksamensprojekt.service.EmployeeService;
import gruppe6.eksamensprojekt.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceTests {


    @Test
    void testCreateEmployee() {
        //Arrange
        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        //Act
        employeeService.createEmployee("testName","testJobTitle");
        employee = employeeService.readEmployeeList().get(employeeService.readEmployeeList().size() - 1);

        //Assert
        assertEquals("testName", employee.getEmpName());
    }

    @Test
    void testReadEmployee() {
        //Arrange
        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        //Act
        employeeService.createEmployee("testName","testJobTitle");
        employee = employeeService.readEmployeeList().get(employeeService.readEmployeeList().size() - 1);

        //Assert
        assertEquals("testName", employee.getEmpName());
    }

    @Test
    void testDeleteEmployee() {
        //Arrange
        EmployeeService employeeService = new EmployeeService();
        Employee employee;
        //Act
        employeeService.createEmployee("testNameDelete","testJobTitleDelete");
        employee = employeeService.readEmployeeList().get(employeeService.readEmployeeList().size() - 1);
        employeeService.deleteEmployee(employee.getId());
        employee = readEmployeeById(employeeService.readEmployeeList(), employee.getId());
        //Assert
        assertNull(employee);
    }

    Employee readEmployeeById(ArrayList<Employee> employeeList, int employeeId){
        //Set employee til null
        Employee employee = null;

        //Loop igennem employeeList
        for(int i = 0; i < employeeList.size(); i++){
            //Tjekker om employee id er det samme som vi søger efter
            if(employeeList.get(i).getId() == employeeId){
                //Set employee
                employee = employeeList.get(i);
            }
        }
        return employee;
    }


}
