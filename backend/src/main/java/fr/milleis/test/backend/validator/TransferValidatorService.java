package fr.milleis.test.backend.validator;

import fr.milleis.test.backend.exception.BusinessValidationException;
import fr.milleis.test.backend.exception.ErrorConstants;
import fr.milleis.test.backend.model.dto.TransferRequestDTO;
import fr.milleis.test.backend.model.entity.Account;
import fr.milleis.test.backend.model.enums.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransferValidatorService {

    public void validateTransfer(TransferRequestDTO dto, Account source, Account destination) {
        validateTransfer(dto.getMontant(),source, destination);
        if (dto.getExecutionDate() != null && dto.getExecutionDate().isBefore(LocalDate.now())) {
            throw new BusinessValidationException(
                    ErrorConstants.INVALID_EXECUTION_DATE_CODE,
                    ErrorConstants.INVALID_EXECUTION_DATE_MSG
            );
        }
    }
    public void validateTransfer(BigDecimal amount, Account source, Account destination) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessValidationException(ErrorConstants.INVALID_AMOUNT_CODE, ErrorConstants.INVALID_AMOUNT_MSG);
        }
        if (source == null || destination == null) {
            throw new BusinessValidationException(ErrorConstants.ACCOUNT_NOT_FOUND_CODE, ErrorConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        if (source.getType() == AccountType.EPARGNE) {
            throw new BusinessValidationException(ErrorConstants.NON_DEBITABLE_CODE, ErrorConstants.NON_DEBITABLE_MSG);
        }
        if (source.getMontant().compareTo(amount) < 0) {
            throw new BusinessValidationException(ErrorConstants.INSUFFICIENT_FUNDS_CODE, ErrorConstants.INSUFFICIENT_FUNDS_MSG);
        }
        if (source.getId().equals(destination.getId())) {
            throw new BusinessValidationException(ErrorConstants.INVALID_ACCOUNT_CODE, ErrorConstants.INVALID_ACCOUNT_MSG);
        }
    }
}