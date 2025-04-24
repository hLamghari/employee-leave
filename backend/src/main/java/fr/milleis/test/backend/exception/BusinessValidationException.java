package fr.milleis.test.backend.exception;

import lombok.Getter;

@Getter
public class BusinessValidationException extends ApiException {
    public BusinessValidationException(String code, String message) {
        super(code, message);
    }
}