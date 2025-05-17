package fr.milleis.test.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.milleis.test.backend.enums.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class EmployeeLeavesResponse {
    private Long id;
    private List<LeaveResponse> leaves;
}
