package org.example.deliverymatch.service;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.model.Annonce;
import org.example.deliverymatch.model.Conducteur;
import org.example.deliverymatch.model.Utilisateur;
import org.example.deliverymatch.repository.AnnonceRepository;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;
    private  final ModelMapper modelMapper;
    public AnnonceDto publierAnnonce(AnnonceDto annonceDto) {
        Conducteur conducteur = utilisateurRepository.findById(annonceDto.getConducteurId())
                .filter(u -> u instanceof Conducteur)
                .map(u -> (Conducteur) u)
                .orElseThrow(() -> new RuntimeException("Conducteur not found"));
        Annonce annonce = modelMapper.map(annonceDto, Annonce.class);
        annonce.setConducteur(conducteur);
        Annonce savedAnnonce = annonceRepository.save(annonce);
        AnnonceDto resultDto = modelMapper.map(savedAnnonce, AnnonceDto.class);
        resultDto.setConducteurId(conducteur.getId());
        return resultDto;
    }
    public List<AnnonceDto> getAnnoncesByDestination(String destination) {
        List<Annonce> annonces = annonceRepository.findByDestination(destination);
        return annonces.stream()
                .map(a -> {
                    AnnonceDto dto = modelMapper.map(a, AnnonceDto.class);
                    dto.setIdAnnonce(a.getIdAnnonce()); // correspondance entre entité et DTO
                    if (a.getConducteur() != null) {
                        dto.setConducteurId(a.getConducteur().getId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }


}
