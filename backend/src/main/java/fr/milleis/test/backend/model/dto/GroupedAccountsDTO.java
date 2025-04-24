package fr.milleis.test.backend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupedAccountsDTO {
    private List<AccountDTO> currentAccounts;
    private List<AccountDTO> savingsAccounts;
}
