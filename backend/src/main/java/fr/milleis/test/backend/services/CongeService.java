package fr.milleis.test.backend.services;

import fr.milleis.test.backend.entities.Employee;
import fr.milleis.test.backend.exceptions.CongeException;
import fr.milleis.test.backend.model.Conge;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CongeService {
    // Créer une nouvelle demande de congé
    public Conge creerConge(Conge congeRequest, Employee employe) throws CongeException {
        // Calculer la durée du congé
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(congeRequest.getDateDebut(), congeRequest.getDateFin()) + 1;

        if (daysBetween <= 0) {
            throw new CongeException("La durée du congé doit être supérieure à 0");
        }

        // Vérification du solde de congés et mise à jour du solde
        if ("RTT".equals(congeRequest.getTypeConge())) {
            if (employe.getSoldeRTT().compareTo(BigDecimal.valueOf(daysBetween)) < 0) {
                throw new CongeException("Solde de RTT insuffisant");
            }
            employe.setSoldeRTT(employe.getSoldeRTT().subtract(BigDecimal.valueOf(daysBetween)));
        } else if ("CONGE_PAYE".equals(congeRequest.getTypeConge())) {
            if (employe.getSoldeConges().compareTo(BigDecimal.valueOf(daysBetween)) < 0) {
                throw new CongeException("Solde de congés payés insuffisant");
            }
            employe.setSoldeConges(employe.getSoldeConges().subtract(BigDecimal.valueOf(daysBetween)));
        }

        // Créer et retourner l'objet Conge
        Conge conge = new Conge();
        conge.setEmploye(employe);
        conge.setDateDebut(congeRequest.getDateDebut());
        conge.setDateFin(congeRequest.getDateFin());
        conge.setTypeConge(congeRequest.getTypeConge());

        return conge;
    }
}
