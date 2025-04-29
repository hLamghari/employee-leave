package fr.milleis.test.backend.dto;

import fr.milleis.test.backend.enums.TypeConge;

import java.time.LocalDate;

public class DemandeCongeDTO {
    private Long employeId;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private TypeConge typeConge;
}
