package fr.techl.backend.services.impl;

import fr.techl.backend.dto.mapper.LeaveMapper;
import fr.techl.backend.dto.response.EmployeeLeavesResponse;
import fr.techl.backend.dto.response.EmployeeResponse;
import fr.techl.backend.dto.mapper.EmployeeMapper;
import fr.techl.backend.entities.Employee;
import fr.techl.backend.repositories.EmployeeRepository;
import fr.techl.backend.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final LeaveMapper leaveMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, LeaveMapper leaveMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.leaveMapper = leaveMapper;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employee -> {
            return employee.isExecutive() ? employeeMapper.toExecutiveEmployee(employee) : employeeMapper.toNonExecutiveEmployee(employee);
        }).toList();
    }

    @Override
    public Optional<EmployeeResponse> getEmployeeById(Long id) {
        return getById(id).map(employee ->
                employee.isExecutive() ? employeeMapper.toExecutiveEmployee(employee) : employeeMapper.toNonExecutiveEmployee(employee));
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<EmployeeLeavesResponse> getEmployeeLeaves(Long id) {
        return getById(id).map(employee -> EmployeeLeavesResponse.builder().id(id).leaves(employee.getLeaves().stream().map(leaveMapper::toLeaveDto).toList()).build());
    }
}
