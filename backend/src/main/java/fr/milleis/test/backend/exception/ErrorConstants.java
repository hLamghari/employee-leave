package fr.milleis.test.backend.exception;

public final class ErrorConstants {
    private ErrorConstants() { }


    public static final String ERR_USER_INPUT_INVALID_CODE       = "ERR_INPUT_100";
    public static final String ERR_USER_INPUT_INVALID_MESSAGE    = "Validation error: please verify your input.";

    public static final String INTERNAL_SERVER_ERROR_CODE = "INTERNAL_SERVER_ERROR";
    public static final String INTERNAL_SERVER_ERROR_MSG = "An unexpected error occurred. Please try again later.";

    public static final String INSUFFICIENT_FUNDS_CODE = "ER000001";
    public static final String INSUFFICIENT_FUNDS_MSG = "Provision insuffisante sur le compte source.";

    public static final String NON_DEBITABLE_CODE = "ER000002";
    public static final String NON_DEBITABLE_MSG = "Le compte source ne peut pas être débité (compte épargne).";

    public static final String INVALID_AMOUNT_CODE = "ER000003";
    public static final String INVALID_AMOUNT_MSG = "Le montant du virement est invalide.";

    public static final String ACCOUNT_NOT_FOUND_CODE = "ER000004";
    public static final String ACCOUNT_NOT_FOUND_MSG = "Un des comptes n'existe pas.";

    public static final String INVALID_ACCOUNT_CODE = "ER000005";
    public static final String INVALID_ACCOUNT_MSG = "Compte source ou destination invalide pour ce client.";
    public static final String INVALID_EXECUTION_DATE_CODE = "ER000006";
    public static final String INVALID_EXECUTION_DATE_MSG = "La date d'exécution ne peut pas être dans le passé.";


}
