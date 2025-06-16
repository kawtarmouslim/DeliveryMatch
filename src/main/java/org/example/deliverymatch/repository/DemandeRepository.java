package org.example.deliverymatch.repository;

import org.example.deliverymatch.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
