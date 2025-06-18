package org.example.deliverymatch.controller;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.DemandeDto;
import org.example.deliverymatch.model.StatutDemande;
import org.example.deliverymatch.service.DemandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demande")
@AllArgsConstructor
public class DemandeController {
    private final DemandeService service;
    @PostMapping("/demander")
    public DemandeDto save(@RequestBody DemandeDto dto) {
        DemandeDto demandeDto=service.demander(dto);
        return demandeDto;

    }
    @PutMapping("/{demandeId}/statut")
    public ResponseEntity<DemandeDto> changerStatutDemande(
            @PathVariable Long demandeId,
            @RequestParam StatutDemande statut,
            @RequestParam Long conducteurId) {

        DemandeDto updatedDemande = service.changerStatutDemande(demandeId, statut, conducteurId);
        return ResponseEntity.ok(updatedDemande);
    }


}
