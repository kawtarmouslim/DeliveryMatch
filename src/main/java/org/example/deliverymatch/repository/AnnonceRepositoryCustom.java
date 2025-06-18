package org.example.deliverymatch.repository;

import org.example.deliverymatch.model.Annonce;

import java.util.List;

public interface AnnonceRepositoryCustom {
    List<Annonce> searchAnnonces(String destination, String typeColis, Boolean blender);
}
