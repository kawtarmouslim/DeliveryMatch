package org.example.deliverymatch.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
//    private Boolean blender;

}
