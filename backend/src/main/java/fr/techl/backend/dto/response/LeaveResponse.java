package fr.techl.backend.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveResponse {
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private EmployeeResponse employee;
}
