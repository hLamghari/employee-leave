package fr.techl.backend.services;

import fr.techl.backend.dto.response.EmployeeLeavesResponse;
import fr.techl.backend.dto.response.EmployeeResponse;
import fr.techl.backend.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployees();

    Optional<EmployeeResponse> getEmployeeById(Long id);

    Optional<Employee> getById(Long id);

    Optional<EmployeeLeavesResponse> getEmployeeLeaves(Long id);
}
