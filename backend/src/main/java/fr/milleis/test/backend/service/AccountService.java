package fr.milleis.test.backend.service;


import fr.milleis.test.backend.model.dto.AccountDTO;
import fr.milleis.test.backend.model.dto.GroupedAccountsDTO;

public interface AccountService {
    GroupedAccountsDTO getAccountsByUserId(Long userId);
    AccountDTO getAccountById(Long accountId);


}
