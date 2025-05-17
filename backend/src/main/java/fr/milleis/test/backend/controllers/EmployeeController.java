package fr.milleis.test.backend.controllers;

import fr.milleis.test.backend.dto.response.EmployeeLeavesResponse;
import fr.milleis.test.backend.dto.response.EmployeeResponse;
import fr.milleis.test.backend.exceptions.EmployeeNotFoundException;
import fr.milleis.test.backend.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeService;

    public EmployeeController(EmployeeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        final List<EmployeeResponse> allEmployees = employeService.getAllEmployees();
        return allEmployees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeService.getEmployeeById(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    @GetMapping("/employees/{id}/leaves")
    public ResponseEntity<EmployeeLeavesResponse> getEmployeesLeaves(@PathVariable Long id) {
        return ResponseEntity.ok(employeService.getEmployeeLeaves(id).orElseThrow(() -> new EmployeeNotFoundException(id)));
    }
}
