package fr.milleis.test.backend.services;

import fr.milleis.test.backend.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployes();

    Optional<Employee> getEmployeById(Long id);
}
