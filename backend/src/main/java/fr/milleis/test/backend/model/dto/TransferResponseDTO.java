package fr.milleis.test.backend.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferResponseDTO {
    private Long compteSourceId;
    private Long compteDestinationId;
    private BigDecimal montant;
    private LocalDate dateExecution;
    private String status;
    private String message;
}