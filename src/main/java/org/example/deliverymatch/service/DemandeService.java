package org.example.deliverymatch.service;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.dto.DemandeDto;
import org.example.deliverymatch.model.Annonce;
import org.example.deliverymatch.model.Demande;
import org.example.deliverymatch.model.Expéditeur;
import org.example.deliverymatch.model.StatutDemande;
import org.example.deliverymatch.repository.AnnonceRepository;
import org.example.deliverymatch.repository.DemandeRepository;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DemandeService {
    private final DemandeRepository demandeRepository;
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ModelMapper modelMapper;
    public DemandeDto demander(DemandeDto dto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Expéditeur expéditeur = (Expéditeur) utilisateurRepository.findByEmail(email).get();

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
    public DemandeDto changerStatutDemande(Long demandeId, StatutDemande nouveauStatut, Long conducteurId) {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        Long conducteurAnnonceId = demande.getAnnonce().getConducteur().getId();
        if (!conducteurAnnonceId.equals(conducteurId)) {
            throw new RuntimeException("Vous n’êtes pas autorisé à modifier cette demande.");
        }

        demande.setStatut(nouveauStatut);
        Demande updated = demandeRepository.save(demande);
        return modelMapper.map(updated, DemandeDto.class);
    }

    public DemandeDto traiterDemande(Long demandeId, boolean accepter, Long conducteurId) {
        Demande demande = demandeRepository.findById(demandeId)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        Long conducteurAnnonceId = demande.getAnnonce().getConducteur().getId();
        if (!conducteurAnnonceId.equals(conducteurId)) {
            throw new RuntimeException("Vous n’êtes pas autorisé à traiter cette demande.");
        }

        if (accepter) {
            Long annonceId = demande.getAnnonce().getIdAnnonce();
            double capaciteMax = demande.getAnnonce().getCapacite();

            List<Demande> demandesAcceptees = demandeRepository.findByAnnonceIdAnnonceAndStatut(annonceId, StatutDemande.ACCEPTEE);

            double capaciteUtilisee = demandesAcceptees.stream()
                    .mapToDouble(Demande::getDimensions)
                    .sum();

            double capaciteRestante = capaciteMax - capaciteUtilisee;

            if (demande.getDimensions() > capaciteRestante) {
                throw new RuntimeException("Capacité insuffisante pour accepter cette demande.");
            }
        }

        demande.setStatut(accepter ? StatutDemande.ACCEPTEE : StatutDemande.REFUSEE);
        Demande updated = demandeRepository.save(demande);

        return modelMapper.map(updated, DemandeDto.class);
    }

    public List<DemandeDto> getDemande() {
        List<Demande> demandes = demandeRepository.findAll();
        return demandes.stream().map(u->modelMapper.map(u, DemandeDto.class))
                .collect(Collectors.toList());

    }

}
