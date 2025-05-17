package fr.milleis.test.backend.services;

import fr.milleis.test.backend.dto.response.EmployeeLeavesResponse;
import fr.milleis.test.backend.dto.response.EmployeeResponse;
import fr.milleis.test.backend.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();

    Optional<EmployeeResponse> getEmployeeById(Long id);

    Optional<Employee> getById(Long id);

    Optional<EmployeeLeavesResponse> getEmployeeLeaves(Long id);
}
