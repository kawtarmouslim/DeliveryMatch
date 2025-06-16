package org.example.deliverymatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @JoinColumn(name = "idExpediteur")
    private Conducteur expediteur;

}
