package fr.milleis.test.backend.controllers;

import fr.milleis.test.backend.entities.Employee;
import fr.milleis.test.backend.exceptions.EmployeeNotFoundException;
import fr.milleis.test.backend.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeeController {
    private final EmployeeService employeService;

    public EmployeeController(EmployeeService employeService) {
        this.employeService = employeService;
    }

    public List<Employee> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    @GetMapping("/{id}")
    public Employee getEmploye(@PathVariable Long id) throws EmployeeNotFoundException {
        return employeService.getEmployeById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
