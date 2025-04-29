package fr.milleis.test.backend.exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(Long id) {
        super("Employee with id " + id + " not found");
    }
}
