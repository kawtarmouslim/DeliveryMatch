package org.example.deliverymatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDemande;
    private double dimensions;
    private double poids;
    private String type;
    @ManyToOne
    @JoinColumn(name = "annonce_id")
    private Annonce annonce;

    @ManyToOne
    @JoinColumn(name = "id_expediteur")
    private Expéditeur expediteur;

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public double getDimensions() {
        return dimensions;
    }

    public void setDimensions(double dimensions) {
        this.dimensions = dimensions;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Expéditeur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Expéditeur expediteur) {
        this.expediteur = expediteur;
    }
}
