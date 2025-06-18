package org.example.deliverymatch.repository;

import org.example.deliverymatch.model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByDestination(String destination);
}
