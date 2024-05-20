package com.entrenamientos.employees.repositories;

import com.entrenamientos.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
