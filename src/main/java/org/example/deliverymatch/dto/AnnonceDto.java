package org.example.deliverymatch.dto;

import lombok.*;
import org.example.deliverymatch.model.Annonce;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnnonceDto implements Serializable {
    Long idAnnonce;
    String depart;
    String etape;
    String destination;
    int capacite;
    double dimension;
    String typeColis;
    private Long conducteurId;
    private Boolean blender;

}