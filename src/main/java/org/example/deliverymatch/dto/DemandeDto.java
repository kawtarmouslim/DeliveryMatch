package org.example.deliverymatch.dto;

import lombok.Value;
import org.example.deliverymatch.model.Demande;

import java.io.Serializable;

/**
 * DTO for {@link Demande}
 */
@Value
public class DemandeDto implements Serializable {
    int idDemande;
    double dimensions;
    double poids;
    String type;
    Long annonceIdAnnonce;
}