package com.entrenamientos.employees.services;

import com.entrenamientos.employees.exceptions.ResourceNotFound;
import com.entrenamientos.employees.models.Employee;
import com.entrenamientos.employees.repositories.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return iEmployeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long id) {
        Employee employee = iEmployeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("El cliente solicitado con el id" + String.valueOf(id) + "no existe"));
        return  ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<Employee> editEmployeeById(Long id, Employee employeeRequest) {
        Employee employee = iEmployeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("El cliente solicitado con el id" + String.valueOf(id) + "no existe"));
        employee.setName(employeeRequest.getName());
        employee.setCity(employeeRequest.getCity());
        employee.setAge(employeeRequest.getAge());
        employee.setCarrer_level(employeeRequest.getCarrer_level());

        Employee employeeUpdated = iEmployeeRepository.save(employee);

        return ResponseEntity.ok(employeeUpdated);

    }

    @Override
    public ResponseEntity<Map<String, Boolean>> removeEmployeeById(Long id) {
        Employee employee = iEmployeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("El cliente solicitado con el id" + String.valueOf(id) + "no existe"));
        iEmployeeRepository.delete(employee);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }
}
