package com.entrenamientos.employees.services;

import com.entrenamientos.employees.models.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {

    // CRUD methods template

    // Create a new employee
    Employee saveEmployee(Employee employee);

    // Read all employees
    List<Employee> getAllEmployees();

    // Read employee by Id
    ResponseEntity<Employee> getEmployeeById(Long id);

    // Update an employee by Id
    ResponseEntity<Employee> editEmployeeById(Long id, Employee employeeRequest);

    // Delete an employee by Id
    ResponseEntity<Map<String, Boolean>> removeEmployeeById(Long id);


}
