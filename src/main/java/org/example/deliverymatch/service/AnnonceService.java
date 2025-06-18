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

        Conducteur conducteur = (Conducteur) utilisateurRepository.findById(annonceDto.getConducteurId())
                .orElseThrow(() -> new RuntimeException("Conducteur not found"));

        Annonce annonce = modelMapper.map(annonceDto, Annonce.class);

        annonce.setConducteur(conducteur);

        Annonce savedAnnonce = annonceRepository.save(annonce);

        AnnonceDto resultDto = modelMapper.map(savedAnnonce, AnnonceDto.class);

        resultDto.setConducteurId(conducteur.getId());

        return resultDto;
    }


    public List<AnnonceDto> searchAnnonces(String destination, String typeColis) {
        return annonceRepository.searchAnnonces(destination, typeColis)
                .stream()
                .map(a -> {
                    AnnonceDto dto = modelMapper.map(a, AnnonceDto.class);
                    dto.setIdAnnonce(a.getIdAnnonce());
                    dto.setConducteurId(a.getConducteur() != null ? a.getConducteur().getId() : null);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public  AnnonceDto updateAnnonce( Long id,AnnonceDto annonceDto) {
        Annonce annonce=annonceRepository.findById(id).get();

        annonce.setCapacite(annonceDto.getCapacite());
        annonce.setDepart(annonceDto.getDepart());
        annonce.setDestination(annonceDto.getDestination());
        annonce.setTypeColis(annonceDto.getTypeColis());
        annonce.setDimension(annonceDto.getDimension());
        annonce.setConducteur(annonce.getConducteur());
        annonce.setEtape(annonceDto.getEtape());
        Annonce savedAnnonce = annonceRepository.save(annonce);
        return modelMapper.map(savedAnnonce, AnnonceDto.class);

    }
    public void deleteAnnonce(Long id) {

         annonceRepository.deleteById(id);
     }


}
