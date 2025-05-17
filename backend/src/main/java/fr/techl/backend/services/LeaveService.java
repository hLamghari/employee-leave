package fr.techl.backend.services;

import fr.techl.backend.dto.request.LeaveDemand;
import fr.techl.backend.dto.response.LeaveResponse;
import fr.techl.backend.exceptions.EmployeeNotFoundException;
import fr.techl.backend.exceptions.LeaveException;


public interface LeaveService {
    LeaveResponse createLeave(LeaveDemand leaveDemand) throws LeaveException, EmployeeNotFoundException;
}
