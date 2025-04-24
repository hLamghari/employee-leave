package fr.milleis.test.backend.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransferRequestDTO {
    private Long sourceAccountId;
    private Long destinationAccountId;
    private BigDecimal montant;
    private LocalDate executionDate;
    private String periodicite;
}