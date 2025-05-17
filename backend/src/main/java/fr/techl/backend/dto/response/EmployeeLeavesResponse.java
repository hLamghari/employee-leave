package fr.techl.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
