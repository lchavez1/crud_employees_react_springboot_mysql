package com.entrenamientos.employees.controllers;

import com.entrenamientos.employees.models.Employee;
import com.entrenamientos.employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> editEmployeeById(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.editEmployeeById(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> removeEmployeeById(@PathVariable Long id){
        return employeeService.removeEmployeeById(id);
    }

    // Example 1. Return a list of employees from Monterrey
    @GetMapping("/employees/city/{city}")
    public List<Employee> getEmployeesByCity(@PathVariable String city){
        return employeeService.getAllEmployees().stream().filter(e -> e.getCity().equals(city)).toList();
    }

    // Example 3. Return a map of employees, group by city and save a list of employees as value
    @GetMapping("/employees/grouped-city")
    public Map<String, List<Employee>> groupEmployeesByCity(){
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.mapping(employee -> employee, Collectors.toList())));
    }

    // Example. Return a map of all the cities and the number of employees from each city
    @GetMapping("/employees/cities")
    public Map<String, Long> getAllCities(){
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.counting()));
    }
}
