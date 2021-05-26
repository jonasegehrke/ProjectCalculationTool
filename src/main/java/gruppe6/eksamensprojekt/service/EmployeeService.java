package gruppe6.eksamensprojekt.service;

import gruppe6.eksamensprojekt.data.EmployeeMapper;
import gruppe6.eksamensprojekt.domain.model.Employee;

import java.util.ArrayList;

public class EmployeeService {

    EmployeeMapper employeeMapper = new EmployeeMapper();
    ArrayList<Employee> employeeList;

    /**
     * Creates an employee
     * @param empName is a string containing the name of the employee
     * @param jobTitle is a string containing the employees job title
     */
    public void createEmployee(String empName, String jobTitle){
        if (empName.trim().length() > 0 && jobTitle.trim().length() > 0) {
            Employee employee = new Employee(empName, jobTitle);
            employeeMapper.createEmployee(employee);
        }
    }

    /**
     * Reads all the employees from a list
     * @return returns a new list of employees
     */
    public ArrayList readEmployeeList(){
        employeeList = employeeMapper.readAllEmployees();
        return employeeList;
    }

    /**
     * Adds hours to a specific employee
     * @param employeeId is the ID used to sort through the employees
     * @param hours is the amount of hours supposed to be added to the employee
     */
    public void addHoursToEmployee(int employeeId, double hours){
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getId() == employeeId){
                double previousHours = employeeList.get(i).getPlannedHours();
                employeeList.get(i).setPlannedHours(previousHours + hours);
                employeeMapper.editEmployeeHours(employeeList.get(i));
            }
        }
    }

    /**
     * Deducts hours from a specific employee
     * @param employeeId is the ID used to sort through the employees
     * @param hours is the amount of hours supposed to be deducted to the employee
     */
    public void deductHoursFromEmployee(int employeeId, double hours){
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getId() == employeeId){
                double previousHours = employeeList.get(i).getPlannedHours();
                employeeList.get(i).setPlannedHours(previousHours - hours);
                employeeMapper.editEmployeeHours(employeeList.get(i));
            }
        }
    }

    /**
     * Deletes an employee from the employee list
     * @param employeeId is the ID used to sort through the employees
     */
    public void deleteEmployee(int employeeId){
        for(int i = 0; i < employeeList.size(); i++){
            if(employeeList.get(i).getId() == employeeId){
                employeeMapper.deleteEmployee(employeeList.get(i));
            }
        }
    }



}
