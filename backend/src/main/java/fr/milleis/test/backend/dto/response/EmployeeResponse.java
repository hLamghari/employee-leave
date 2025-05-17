package fr.milleis.test.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.milleis.test.backend.enums.Category;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
    private Long id;
    private String nom;
    private String prenom;
    private Category categorie;
    private LocalDate dateEmbauche;
    private Double soldeConges;
    private Double soldeRTT;
}
