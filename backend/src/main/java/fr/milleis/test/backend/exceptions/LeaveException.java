package fr.milleis.test.backend.exceptions;

public class LeaveException extends RuntimeException {
    public LeaveException(String message) {
        super(message);
    }
}
