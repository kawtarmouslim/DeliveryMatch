package org.example.deliverymatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnonce;

    private String depart;
    private String etape;
    private String destination;
    private int capacite;
    private double dimension;
    private String typeColis;

    @ManyToOne
    @JoinColumn(name = "conducteur_id") // nom correct pour la colonne
    private Conducteur conducteur; //

}
