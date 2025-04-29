package fr.milleis.test.backend.entities;

import fr.milleis.test.backend.enums.Categorie;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private LocalDate dateEmbauche;
    private BigDecimal soldeConges;
    private BigDecimal soldeRTT;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public BigDecimal getSoldeConges() {
        return soldeConges;
    }

    public void setSoldeConges(BigDecimal soldeConges) {
        this.soldeConges = soldeConges;
    }

    public BigDecimal getSoldeRTT() {
        return soldeRTT;
    }

    public void setSoldeRTT(BigDecimal soldeRTT) {
        this.soldeRTT = soldeRTT;
    }
}
