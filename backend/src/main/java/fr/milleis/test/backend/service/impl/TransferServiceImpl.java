package fr.milleis.test.backend.service.impl;


import fr.milleis.test.backend.exception.BusinessValidationException;
import fr.milleis.test.backend.exception.ErrorConstants;
import fr.milleis.test.backend.model.dto.TransferRequestDTO;
import fr.milleis.test.backend.model.dto.TransferResponseDTO;
import fr.milleis.test.backend.model.entity.Account;
import fr.milleis.test.backend.model.entity.Transfer;
import fr.milleis.test.backend.model.enums.TransfertStatus;
import fr.milleis.test.backend.repository.AccountRepository;
import fr.milleis.test.backend.repository.TransferRepository;
import fr.milleis.test.backend.service.TransferService;
import fr.milleis.test.backend.validator.TransferValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private TransferValidatorService validatorService;


    @Override
    @Transactional
    public TransferResponseDTO makeTransfer(TransferRequestDTO request) {
        log.info("Transfer request received: {}", request);

        Account source = fetchAccountOrThrow(request.getSourceAccountId());
        Account destination = fetchAccountOrThrow(request.getDestinationAccountId());

        validatorService.validateTransfer(request, source, destination);

        LocalDate executionDate = request.getExecutionDate() != null ? request.getExecutionDate() : LocalDate.now();

        Transfer transfer;

        if (!executionDate.isAfter(LocalDate.now())) {
            // Immediate execution
            moveFunds(source, destination, request.getMontant());
            transfer = storeTransfer(request, source, destination, TransfertStatus.EXECUTED, executionDate);
            log.info("Transfer executed and stored with id {}", transfer.getId());
        } else {
            // Future-dated: persist as PENDING
            transfer = storeTransfer(request, source, destination, TransfertStatus.PENDING, executionDate);
            log.info("Transfer scheduled (pending) and stored with id {}", transfer.getId());
        }

        return buildSuccessResponse();
    }

    @Transactional
    public void executeScheduledTransfer(Transfer transfer) {
        Account source = transfer.getSourceAccount();
        Account destination = transfer.getDestinationAccount();
        BigDecimal amount = transfer.getAmount();

        validatorService.validateTransfer(amount, source, destination);
        moveFunds(source, destination, amount);

        transfer.setStatus(TransfertStatus.EXECUTED);
        transferRepository.save(transfer);
    }

    private Account fetchAccountOrThrow(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessValidationException(
                        ErrorConstants.ACCOUNT_NOT_FOUND_CODE,
                        ErrorConstants.ACCOUNT_NOT_FOUND_MSG
                ));
    }

    private void moveFunds(Account source, Account destination, BigDecimal amount) {
        source.setMontant(source.getMontant().subtract(amount));
        destination.setMontant(destination.getMontant().add(amount));
        accountRepository.save(source);
        accountRepository.save(destination);
    }

    private Transfer storeTransfer(TransferRequestDTO request, Account source, Account destination, TransfertStatus status, LocalDate executionDate) {
        Transfer transfer = new Transfer();
        transfer.setSourceAccount(source);
        transfer.setDestinationAccount(destination);
        transfer.setAmount(request.getMontant());
        transfer.setExecutionDate(executionDate);
        transfer.setPeriodicity(request.getPeriodicite());
        transfer.setStatus(status);
        return transferRepository.save(transfer);
    }
    private TransferResponseDTO buildSuccessResponse() {
        TransferResponseDTO response = new TransferResponseDTO();
        response.setStatus("SUCCESS");
        response.setMessage("Transfer completed and stored successfully.");
        return response;
    }
}