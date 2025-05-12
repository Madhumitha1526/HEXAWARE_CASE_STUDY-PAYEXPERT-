package com.hexaware.dao;

import java.util.List;
import com.hexaware.model.Employee;

// Interface for Employee Service
public interface IEmployeeService {

    // Retrieve an employee by their ID
    Employee getEmployeeById(int employeeId);

    // Retrieve a list of all employees
    List<Employee> getAllEmployees();

    // Add a new employee to the database
    boolean addEmployee(Employee employee);

    // Update an existing employee's information
    boolean updateEmployee(Employee employee);

    // Remove an employee from the database
    boolean removeEmployee(int employeeId);
}