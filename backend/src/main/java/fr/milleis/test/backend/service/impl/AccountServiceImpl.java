package fr.milleis.test.backend.service.impl;


import fr.milleis.test.backend.exception.ErrorConstants;
import fr.milleis.test.backend.exception.ResourceNotFoundException;
import fr.milleis.test.backend.mapper.AccountMapper;
import fr.milleis.test.backend.model.dto.AccountDTO;
import fr.milleis.test.backend.model.dto.GroupedAccountsDTO;
import fr.milleis.test.backend.model.entity.Account;
import fr.milleis.test.backend.model.enums.AccountType;
import fr.milleis.test.backend.repository.AccountRepository;
import fr.milleis.test.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public GroupedAccountsDTO getAccountsByUserId(Long userId) {
        log.info("Fetching accounts for userId: {}", userId);
        List<Account> accounts = accountRepository.findByUserId(userId);
        if (accounts.isEmpty()) {
            log.warn("No accounts found for userId: {}", userId);
            throw new ResourceNotFoundException(
                    ErrorConstants.ACCOUNT_NOT_FOUND_CODE,
                    ErrorConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        log.info("Found {} accounts for userId: {}", accounts.size(), userId);

        GroupedAccountsDTO groupedAccounts = new GroupedAccountsDTO();
        groupedAccounts.setCurrentAccounts(mapAccountsByType(accounts, AccountType.COURANT));
        groupedAccounts.setSavingsAccounts(mapAccountsByType(accounts, AccountType.EPARGNE));
        return groupedAccounts;
    }

    @Override
    public AccountDTO getAccountById(Long accountId) {
        log.info("Fetching account details for accountId: {}", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    log.warn("Account not found for id: {}", accountId);
                    return new ResourceNotFoundException(
                            ErrorConstants.ACCOUNT_NOT_FOUND_CODE,
                            ErrorConstants.ACCOUNT_NOT_FOUND_MSG
                    );
                });
        log.info("Account found: {}", account.getIban());
        return accountMapper.toDto(account);
    }

    private List<AccountDTO> mapAccountsByType(List<Account> accounts, AccountType type) {
        return accounts.stream()
                .filter(a -> a.getType() == type)
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }
}