package org.example.deliverymatch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DemandeDto implements Serializable {
    int idDemande;
    double dimensions;
    double poids;
    String type;
    Long annonceIdAnnonce;
    Long expediteurId;
   
}