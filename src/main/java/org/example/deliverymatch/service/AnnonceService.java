package org.example.deliverymatch.service;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.dto.DemandeDto;
import org.example.deliverymatch.model.*;
import org.example.deliverymatch.repository.AnnonceRepository;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.example.deliverymatch.model.QDemande.demande;
import static org.example.deliverymatch.model.QExpéditeur.expéditeur;

@Service
@AllArgsConstructor
public class AnnonceService {
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;
    private  final ModelMapper modelMapper;

//    public AnnonceDto publierAnnonce(AnnonceDto annonceDto) {
//        String email = SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);
//        Conducteur conducteur = optionalUtilisateur
//                .filter(u -> u instanceof Conducteur)
//                .map(u -> (Conducteur) u)
//                .orElseThrow(() -> new RuntimeException("Conducteur non trouvé ou ID manquant pour l'email : " + email));
//
//        Annonce annonce = new Annonce();
//        annonce.setIdAnnonce(annonceDto.getIdAnnonce());
//        annonce.setDepart(annonceDto.getDepart());
//        annonce.setEtape(annonceDto.getEtape());
//        annonce.setDestination(annonceDto.getDestination());
//        annonce.setCapacite(annonceDto.getCapacite());
//        annonce.setDimension(annonceDto.getDimension());
//        annonce.setTypeColis(annonceDto.getTypeColis());
//        annonce.setConducteur(conducteur);
//
//        Annonce savedAnnonce = annonceRepository.save(annonce);
//        AnnonceDto resultDto = modelMapper.map(savedAnnonce, AnnonceDto.class);
//        resultDto.setConducteur_id(conducteur.getId()); // Forcer la valeur
//        return resultDto;
//    }
public AnnonceDto publierAnnonce(AnnonceDto dto) {

    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Conducteur conducteur = (Conducteur) utilisateurRepository.findByEmail(email).get();



    Annonce annonce = modelMapper.map(dto, Annonce.class);
    annonce.setConducteur(conducteur);

    Annonce saveAnonce=annonceRepository.save(annonce);
    AnnonceDto rsult=modelMapper.map(saveAnonce, AnnonceDto.class);
    rsult.setConducteurId(saveAnonce.getConducteur().getId());

    return rsult;
}
    public List<AnnonceDto> searchAnnonces(String destination, String typeColis) {
        return annonceRepository.searchAnnonces(destination, typeColis)
                .stream()
                .map(a -> {
                    AnnonceDto dto = modelMapper.map(a, AnnonceDto.class);
                    dto.setIdAnnonce(a.getIdAnnonce());
//                    dto.setConducteurId(a.getConducteur() != null ? a.getConducteur().getId() : null);
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

     public List<AnnonceDto> getAnnonces() {
        List<Annonce> annonces = annonceRepository.findAll();
     return annonces.stream().map(u->modelMapper.map(u, AnnonceDto.class))
             .collect(Collectors.toList());

     }


}
