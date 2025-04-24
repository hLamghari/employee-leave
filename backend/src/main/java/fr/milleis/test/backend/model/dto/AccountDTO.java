package fr.milleis.test.backend.model.dto;

import fr.milleis.test.backend.model.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;
    private String nom;
    private String iban;
    private AccountType type;
    private BigDecimal montant;
    private BigDecimal tauxInteret;
}