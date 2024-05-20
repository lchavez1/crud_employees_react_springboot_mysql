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
//@CrossOrigin(origins = "http://localhost:3000")
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

    // Example 2. Return a list of employees younger than 30
    @GetMapping("/employees/age/{age}")
    public List<Employee> getEmployeesByAge(@PathVariable Integer age){
        return employeeService.getAllEmployees().stream().filter(e -> e.getAge() < 30).toList();
    }

    // Example 3. Return a map of employees, group by city and save their names as value
    @GetMapping("/employees/grouped-city")
    public Map<String, List<Employee>> groupEmployeesByCity(){
        return employeeService.getAllEmployees().stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.mapping(employee -> employee, Collectors.toList())));
    }

    // Example 4. Return the number of employees from Merida
    @GetMapping("/employees/number/{city}")
    public Long employeesPerCity(@PathVariable String city){
        return employeeService.getAllEmployees().stream().filter(employee -> employee.getCity().equals(city)).count();
    }

    // Example 8. Return a list of all the cities, no duplicated values
    @GetMapping("/employees/cities")
    public List<String> getAllCities(){
        return employeeService.getAllEmployees().stream().map(Employee::getCity).distinct().toList();
    }
}
