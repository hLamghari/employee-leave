package fr.milleis.test.backend.services.impl;

import fr.milleis.test.backend.entities.Employee;
import fr.milleis.test.backend.repositories.EmployeeRepository;
import fr.milleis.test.backend.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployes() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeById(Long id) {
        return employeeRepository.findById(id);
    }
}
