package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.EmployeeMapper;
import gruppe6.eksamensprojekt.domain.model.Employee;

import java.util.ArrayList;

public class EmployeeService {

    EmployeeMapper employeeMapper = new EmployeeMapper();
    ArrayList<Employee> employeeList;

    public void createEmployee(String empName, String jobTitle){
        if (empName.trim().length() > 0 && jobTitle.trim().length() > 0) {
            Employee employee = new Employee(empName, jobTitle);
            employeeMapper.createEmployee(employee);
        }
    }

    public ArrayList readEmployeeList(){
        employeeList = employeeMapper.readAllEmployees();
        return employeeList;
    }


    public void addHoursToEmployee(int employeeId, double hours){
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getId() == employeeId){
                double previousHours = employeeList.get(i).getPlannedHours();
                employeeList.get(i).setPlannedHours(previousHours + hours);
                employeeMapper.addHoursToEmployee(employeeList.get(i));
            }
        }
    }

}
