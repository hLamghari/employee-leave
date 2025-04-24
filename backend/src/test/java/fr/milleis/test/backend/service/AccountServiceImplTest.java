package fr.milleis.test.backend.service;

import fr.milleis.test.backend.exception.ErrorConstants;
import fr.milleis.test.backend.exception.ResourceNotFoundException;
import fr.milleis.test.backend.mapper.AccountMapper;
import fr.milleis.test.backend.model.dto.AccountDTO;
import fr.milleis.test.backend.model.dto.GroupedAccountsDTO;
import fr.milleis.test.backend.model.entity.Account;
import fr.milleis.test.backend.model.enums.AccountType;
import fr.milleis.test.backend.repository.AccountRepository;
import fr.milleis.test.backend.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account currentAccount;
    private Account savingsAccount;
    private AccountDTO currentAccountDTO;
    private AccountDTO savingsAccountDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        currentAccount = new Account();
        currentAccount.setId(1L);
        currentAccount.setNom("Current");
        currentAccount.setIban("CUR123");
        currentAccount.setType(AccountType.COURANT);
        currentAccount.setMontant(BigDecimal.valueOf(1000));

        savingsAccount = new Account();
        savingsAccount.setId(2L);
        savingsAccount.setNom("Savings");
        savingsAccount.setIban("SAV456");
        savingsAccount.setType(AccountType.EPARGNE);
        savingsAccount.setMontant(BigDecimal.valueOf(2000));

        currentAccountDTO = new AccountDTO();
        currentAccountDTO.setId(1L);
        currentAccountDTO.setNom("Current");
        currentAccountDTO.setIban("CUR123");
        currentAccountDTO.setType(AccountType.COURANT);
        currentAccountDTO.setMontant(BigDecimal.valueOf(1000));

        savingsAccountDTO = new AccountDTO();
        savingsAccountDTO.setId(2L);
        savingsAccountDTO.setNom("Savings");
        savingsAccountDTO.setIban("SAV456");
        savingsAccountDTO.setType(AccountType.EPARGNE);
        savingsAccountDTO.setMontant(BigDecimal.valueOf(2000));
    }

    @Test
    void testGetAccountsByUserId_Success() {
        List<Account> accountList = Arrays.asList(currentAccount, savingsAccount);

        when(accountRepository.findByUserId(10L)).thenReturn(accountList);
        when(accountMapper.toDto(currentAccount)).thenReturn(currentAccountDTO);
        when(accountMapper.toDto(savingsAccount)).thenReturn(savingsAccountDTO);

        GroupedAccountsDTO grouped = accountService.getAccountsByUserId(10L);

        assertNotNull(grouped);
        assertEquals(1, grouped.getCurrentAccounts().size());
        assertEquals(1, grouped.getSavingsAccounts().size());
        assertEquals(currentAccountDTO, grouped.getCurrentAccounts().get(0));
        assertEquals(savingsAccountDTO, grouped.getSavingsAccounts().get(0));

        verify(accountRepository).findByUserId(10L);
        verify(accountMapper).toDto(currentAccount);
        verify(accountMapper).toDto(savingsAccount);
    }

    @Test
    void testGetAccountsByUserId_NoAccountsFound() {
        when(accountRepository.findByUserId(20L)).thenReturn(Collections.emptyList());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            accountService.getAccountsByUserId(20L);
        });

        assertEquals(ErrorConstants.ACCOUNT_NOT_FOUND_CODE, ex.getCode());
        assertEquals(ErrorConstants.ACCOUNT_NOT_FOUND_MSG, ex.getMessage());
        verify(accountRepository).findByUserId(20L);
    }

    @Test
    void testGetAccountById_Success() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(currentAccount));
        when(accountMapper.toDto(currentAccount)).thenReturn(currentAccountDTO);

        AccountDTO dto = accountService.getAccountById(1L);

        assertNotNull(dto);
        assertEquals(currentAccountDTO, dto);

        verify(accountRepository).findById(1L);
        verify(accountMapper).toDto(currentAccount);
    }

    @Test
    void testGetAccountById_NotFound() {
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () -> {
            accountService.getAccountById(99L);
        });

        assertEquals(ErrorConstants.ACCOUNT_NOT_FOUND_CODE, ex.getCode());
        assertEquals(ErrorConstants.ACCOUNT_NOT_FOUND_MSG, ex.getMessage());
        verify(accountRepository).findById(99L);
    }
}
