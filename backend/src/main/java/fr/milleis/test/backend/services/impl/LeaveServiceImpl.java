package fr.milleis.test.backend.services.impl;

import fr.milleis.test.backend.dto.mapper.EmployeeMapper;
import fr.milleis.test.backend.dto.request.LeaveDemand;
import fr.milleis.test.backend.entities.Employee;
import fr.milleis.test.backend.entities.Leave;
import fr.milleis.test.backend.enums.Category;
import fr.milleis.test.backend.enums.LeaveType;
import fr.milleis.test.backend.exceptions.EmployeeNotFoundException;
import fr.milleis.test.backend.exceptions.LeaveException;
import fr.milleis.test.backend.dto.response.LeaveResponse;
import fr.milleis.test.backend.exceptions.response.ErrorResponse;
import fr.milleis.test.backend.repositories.LeaveRepository;
import fr.milleis.test.backend.services.EmployeeService;
import fr.milleis.test.backend.services.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;
    private final LeaveRepository leaveRepository;

    public LeaveServiceImpl(EmployeeMapper employeeMapper, EmployeeService employeeService, LeaveRepository leaveRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
        this.leaveRepository = leaveRepository;
    }

    // Créer une nouvelle demande de congé
    public LeaveResponse createLeave(LeaveDemand leaveRequest) throws LeaveException, EmployeeNotFoundException {

        // Valider si l'employé existe
        Employee employee = employeeService.getById(leaveRequest.getEmployeeId()).orElseThrow(() -> new EmployeeNotFoundException(leaveRequest.getEmployeeId()));
        if (employee == null) {
             throw new LeaveException("Employé inexistant");
        }

        // Vérification de la catégorie de l'employé pour les RTT
        if (LeaveType.RTT.equals(leaveRequest.getLeaveType()) && !Category.CADRE.equals(employee.getCategory())) {
            throw new LeaveException("Impossibilité d'attribuer des RTT à un employé non-cadre");
        }

        // Vérification du solde de congés
        double accountBalance = (LeaveType.RTT.equals(leaveRequest.getLeaveType()) ? employee.getSaleRTT() : employee.getSaleLeave()).doubleValue();
        if (accountBalance <= 0) {
            throw new LeaveException("Solde de congés insuffisant pour cette demande");
        }

        // Vérification des dates (si la date de fin est après la date de début)
        if (leaveRequest.getStartDate().isAfter(leaveRequest.getEndDate())) {
            throw new LeaveException("La date de début doit être avant la date de fin");
        }

        // Vérifier qu'il n'existe pas de chauvauchement
        List<Leave> leaves = leaveRepository.findByEmployeeId(employee.getId());
        leaves.stream().filter(leave -> {
           // pas de chevauchement si
            // leave.getStartDate().isAfter(leaveRequest.getEndDate()) || leave.getEndDate().isBefore(leaveRequest.getStartDate());
            // chevauchement si ! (leave.getStartDate().isAfter(leaveRequest.getEndDate()) || leave.getEndDate().isBefore(leaveRequest.getStartDate()))
            // ie. !leave.getStartDate().isAfter(leaveRequest.getEndDate()) && !leave.getEndDate().isBefore(leaveRequest.getStartDate())
            return  !leave.getStartDate().isAfter(leaveRequest.getEndDate()) && !leave.getEndDate().isBefore(leaveRequest.getStartDate());

        }).findAny().ifPresent(leave -> { throw new LeaveException("Un chevauchement avec un congé existant a été détecté");});

        // Calculer la durée du congé
        long daysBetween = ChronoUnit.DAYS.between(leaveRequest.getStartDate(), leaveRequest.getEndDate()) + 1;

        if (daysBetween <= 0) {
            throw new LeaveException("La durée du congé doit être supérieure à 0");
        }

        // Vérification du solde de congés et mise à jour du solde
        if (LeaveType.RTT.equals(leaveRequest.getLeaveType())) {
            if (employee.getSaleRTT().compareTo(BigDecimal.valueOf(daysBetween)) < 0) {
                throw new LeaveException("Solde de RTT insuffisant");
            }
            employee.setSaleRTT(employee.getSaleRTT().subtract(BigDecimal.valueOf(daysBetween)));
        } else if (LeaveType.CONGE_PAYE.equals(leaveRequest.getLeaveType())) {
            if (employee.getSaleLeave().compareTo(BigDecimal.valueOf(daysBetween)) < 0) {
                throw new LeaveException("Solde de congés payés insuffisant");
            }
            employee.setSaleLeave(employee.getSaleLeave().subtract(BigDecimal.valueOf(daysBetween)));
        }
        //Enregistrer la demande en base
        Leave leave = Leave.builder()
                .leaveType(leaveRequest.getLeaveType())
                .employee(employee)
                .startDate(leaveRequest.getStartDate())
                .endDate(leaveRequest.getEndDate())
                .build();
        leaveRepository.save(leave);

        // Créer et retourner l'objet Conge
        LeaveResponse leaveResponse = new LeaveResponse();
        leaveResponse.setEmployee(employee.isExecutive() ? employeeMapper.toExecutiveEmployee(employee) : employeeMapper.toNonExecutiveEmployee(employee));
        leaveResponse.setStartDate(leaveRequest.getStartDate());
        leaveResponse.setEndDate(leaveRequest.getEndDate());
        leaveResponse.setLeaveType(leaveRequest.getLeaveType().name());

        return leaveResponse;
    }
}
