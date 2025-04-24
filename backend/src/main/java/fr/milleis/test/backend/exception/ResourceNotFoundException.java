package fr.milleis.test.backend.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String code, String message) {
        super(code, message);
    }
}
