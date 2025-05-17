package fr.techl.backend.dto.request;

import fr.techl.backend.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaveDemand {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
}
