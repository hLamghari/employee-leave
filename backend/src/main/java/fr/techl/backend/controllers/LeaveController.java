package fr.techl.backend.controllers;


import fr.techl.backend.dto.request.LeaveDemand;
import fr.techl.backend.dto.response.LeaveResponse;
import fr.techl.backend.exceptions.EmployeeNotFoundException;
import fr.techl.backend.exceptions.LeaveException;
import fr.techl.backend.services.EmployeeService;
import fr.techl.backend.services.impl.LeaveServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class LeaveController {

    private final LeaveServiceImpl leaveService;


    private final EmployeeService employeService;

    public LeaveController(LeaveServiceImpl leaveService, EmployeeService employeService) {
        this.leaveService = leaveService;
        this.employeService = employeService;
    }

    // Endpoint pour demander un congé
    @PostMapping("/leaves")
    public ResponseEntity<?> demandLeave(@RequestBody LeaveDemand leaveRequest) throws LeaveException, EmployeeNotFoundException {

            // Créer et enregistrer la demande de congé
            LeaveResponse leaveResponse = leaveService.createLeave(leaveRequest);
            return ResponseEntity.ok(leaveResponse);

    }
}
