package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.EmployeeMapper;
import gruppe6.eksamensprojekt.domain.model.Employee;

import java.util.ArrayList;

public class EmployeeService {

    EmployeeMapper employeeMapper = new EmployeeMapper();

    public void createEmployee(String empName, String jobTitle){
        if (empName.trim().length() > 0 && jobTitle.trim().length() > 0) {
            Employee employee = new Employee(empName, jobTitle);
            employeeMapper.createEmployee(employee);
        }
    }

    public ArrayList readEmployeeList(){
        return employeeMapper.readAllEmployees();
    }


}
