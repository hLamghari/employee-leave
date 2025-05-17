package fr.milleis.test.backend.services;

import fr.milleis.test.backend.dto.request.LeaveDemand;
import fr.milleis.test.backend.dto.response.LeaveResponse;
import fr.milleis.test.backend.entities.Leave;
import fr.milleis.test.backend.exceptions.EmployeeNotFoundException;
import fr.milleis.test.backend.exceptions.LeaveException;


public interface LeaveService {
    LeaveResponse createLeave(LeaveDemand leaveDemand) throws LeaveException, EmployeeNotFoundException;
}
