package org.example.deliverymatch.repository;

import org.example.deliverymatch.model.Demande;
import org.example.deliverymatch.model.StatutDemande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findByAnnonceIdAnnonceAndStatut(Long annonceId, StatutDemande statut);
}
