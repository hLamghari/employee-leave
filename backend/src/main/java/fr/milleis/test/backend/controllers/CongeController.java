package fr.milleis.test.backend.controllers;


import fr.milleis.test.backend.entities.Employee;
import fr.milleis.test.backend.exceptions.CongeException;
import fr.milleis.test.backend.exceptions.EmployeeNotFoundException;
import fr.milleis.test.backend.exceptions.response.ErrorResponse;
import fr.milleis.test.backend.model.Conge;
import fr.milleis.test.backend.services.CongeService;
import fr.milleis.test.backend.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CongeController {

    private CongeService congeService;


    private EmployeeService employeService;

    // Endpoint pour demander un congé
    @PostMapping
    public ResponseEntity<?> demanderConge(@RequestBody Conge congeRequest) {
        try {
            // Valider si l'employé existe
            Employee employe = employeService.getEmployeById(congeRequest.getEmployeId()).orElseThrow(() -> new EmployeeNotFoundException(congeRequest.getEmployeId()));
            if (employe == null) {
                return ResponseEntity.badRequest().body(new ErrorResponse("ER001", "Employé inexistant") {
                });
            }

            // Vérification de la catégorie de l'employé pour les RTT
            if ("RTT".equals(congeRequest.getTypeConge()) && !"CADRE".equals(employe.getCategorie())) {
                return ResponseEntity.badRequest().body(new ErrorResponse("ER002", "Impossibilité d'attribuer des RTT à un employé non-cadre"));
            }

            // Vérification du solde de congés
            double soldeDisponible = ("RTT".equals(congeRequest.getTypeConge()) ? employe.getSoldeRTT() : employe.getSoldeConges()).doubleValue();
            if (soldeDisponible <= 0) {
                return ResponseEntity.badRequest().body(new ErrorResponse("ER012345", "Solde de congés insuffisant pour cette demande"));
            }

            // Vérification des dates (si la date de fin est après la date de début)
            if (congeRequest.getDateDebut().isAfter(congeRequest.getDateFin())) {
                return ResponseEntity.badRequest().body(new ErrorResponse("ER003", "La date de début doit être avant la date de fin"));
            }

            // Créer et enregistrer la demande de congé
            Conge conge = congeService.creerConge(congeRequest, employe);
            return ResponseEntity.ok(conge);

        } catch (CongeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("ER004", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorResponse("ER005", "Une erreur interne est survenue"));
        }
    }
}
