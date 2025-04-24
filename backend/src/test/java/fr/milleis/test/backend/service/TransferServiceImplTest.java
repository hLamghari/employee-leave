package fr.milleis.test.backend.service;

import fr.milleis.test.backend.exception.BusinessValidationException;
import fr.milleis.test.backend.exception.ErrorConstants;
import fr.milleis.test.backend.model.dto.TransferRequestDTO;
import fr.milleis.test.backend.model.dto.TransferResponseDTO;
import fr.milleis.test.backend.model.entity.Account;
import fr.milleis.test.backend.model.entity.Transfer;
import fr.milleis.test.backend.model.enums.AccountType;
import fr.milleis.test.backend.repository.AccountRepository;
import fr.milleis.test.backend.repository.TransferRepository;
import fr.milleis.test.backend.service.impl.TransferServiceImpl;
import fr.milleis.test.backend.validator.TransferValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TransferServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private TransferValidatorService validatorService;

    @InjectMocks
    private TransferServiceImpl transferService;

    private Account sourceAccount;
    private Account destinationAccount;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sourceAccount = new Account();
        sourceAccount.setId(1L);
        sourceAccount.setIban("SRC123");
        sourceAccount.setType(AccountType.COURANT);
        sourceAccount.setMontant(new BigDecimal("1000.00"));

        destinationAccount = new Account();
        destinationAccount.setId(2L);
        destinationAccount.setIban("DST456");
        destinationAccount.setType(AccountType.EPARGNE);
        destinationAccount.setMontant(new BigDecimal("500.00"));
    }

    @Test
    public void testMakeTransfer_ImmediateExecution_Success() {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccountId(1L);
        request.setDestinationAccountId(2L);
        request.setMontant(new BigDecimal("150.00"));
        request.setExecutionDate(LocalDate.now());

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));
        when(transferRepository.save(any(Transfer.class))).thenAnswer(i -> i.getArguments()[0]);

        TransferResponseDTO response = transferService.makeTransfer(request);

        verify(validatorService).validateTransfer(request, sourceAccount, destinationAccount);
        verify(accountRepository).save(sourceAccount);
        verify(accountRepository).save(destinationAccount);
        verify(transferRepository).save(any(Transfer.class));

        assertEquals(new BigDecimal("850.00"), sourceAccount.getMontant());
        assertEquals(new BigDecimal("650.00"), destinationAccount.getMontant());
        assertEquals("SUCCESS", response.getStatus());
        assertEquals("Transfer completed and stored successfully.", response.getMessage());
    }

    @Test
    public void testMakeTransfer_FutureExecution_Pending() {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccountId(1L);
        request.setDestinationAccountId(2L);
        request.setMontant(new BigDecimal("150.00"));
        request.setExecutionDate(LocalDate.now().plusDays(5));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(destinationAccount));
        when(transferRepository.save(any(Transfer.class))).thenAnswer(i -> i.getArguments()[0]);

        TransferResponseDTO response = transferService.makeTransfer(request);

        verify(validatorService).validateTransfer(request, sourceAccount, destinationAccount);
        verify(accountRepository, never()).save(sourceAccount);
        verify(accountRepository, never()).save(destinationAccount);
        verify(transferRepository).save(any(Transfer.class));

        // Amounts should remain unchanged
        assertEquals(new BigDecimal("1000.00"), sourceAccount.getMontant());
        assertEquals(new BigDecimal("500.00"), destinationAccount.getMontant());
        assertEquals("SUCCESS", response.getStatus());
        assertEquals("Transfer completed and stored successfully.", response.getMessage());
    }

    @Test
    public void testMakeTransfer_SourceAccountNotFound() {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccountId(1L);
        request.setDestinationAccountId(2L);
        request.setMontant(new BigDecimal("150.00"));
        request.setExecutionDate(LocalDate.now());

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        BusinessValidationException exception = assertThrows(BusinessValidationException.class, () -> {
            transferService.makeTransfer(request);
        });

        assertEquals(ErrorConstants.ACCOUNT_NOT_FOUND_CODE, exception.getCode());
    }

    @Test
    public void testMakeTransfer_DestinationAccountNotFound() {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccountId(1L);
        request.setDestinationAccountId(2L);
        request.setMontant(new BigDecimal("150.00"));
        request.setExecutionDate(LocalDate.now());

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());

        BusinessValidationException exception = assertThrows(BusinessValidationException.class, () -> {
            transferService.makeTransfer(request);
        });

        assertEquals(ErrorConstants.ACCOUNT_NOT_FOUND_CODE, exception.getCode());
    }
}
