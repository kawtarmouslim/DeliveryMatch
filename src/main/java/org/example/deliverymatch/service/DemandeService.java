package org.example.deliverymatch.service;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.dto.DemandeDto;
import org.example.deliverymatch.model.Annonce;
import org.example.deliverymatch.model.Demande;
import org.example.deliverymatch.model.Expéditeur;
import org.example.deliverymatch.repository.AnnonceRepository;
import org.example.deliverymatch.repository.DemandeRepository;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DemandeService {
    private final DemandeRepository demandeRepository;
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ModelMapper modelMapper;
    public DemandeDto demander(DemandeDto dto) {

        Expéditeur expéditeur= (Expéditeur) utilisateurRepository.findById(dto.getExpediteurId())
                .orElseThrow(() -> new RuntimeException("Expediteur not found"));

        Annonce annonce=annonceRepository.findById(dto.getAnnonceIdAnnonce())
                .orElseThrow(() -> new RuntimeException("annonce not found"));

        Demande demande = modelMapper.map(dto, Demande.class);
        demande.setExpediteur(expéditeur);
        demande.setAnnonce(annonce);
        Demande saveDemande=demandeRepository.save(demande);
        DemandeDto rsult=modelMapper.map(saveDemande, DemandeDto.class);
        rsult.setExpediteurId(expéditeur.getId());
        rsult.setAnnonceIdAnnonce(annonce.getIdAnnonce());
        return rsult;
    }

}
