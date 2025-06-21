package org.example.deliverymatch.service;

import lombok.AllArgsConstructor;
import org.example.deliverymatch.dto.UtilisateurDto;
import org.example.deliverymatch.model.Conducteur;
import org.example.deliverymatch.model.Expéditeur;
import org.example.deliverymatch.model.Utilisateur;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private  final ModelMapper modelMapper;
//    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto) {
//        Utilisateur utilisateur;
//
//        switch (utilisateurDto.getRole()) {
//            case CONDUCTEUR:
//                utilisateur = modelMapper.map(utilisateurDto, Conducteur.class);
//                break;
//            case EXPEDITEUR:
//                utilisateur = modelMapper.map(utilisateurDto, Expéditeur.class);
//                break;
//
//            default:
//                throw new IllegalArgumentException("Rôle inconnu : " + utilisateurDto.getRole());
//        }
//
//        Utilisateur saved = utilisateurRepository.save(utilisateur);
//        return modelMapper.map(saved, UtilisateurDto.class);
//    }

    public List<UtilisateurDto> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs=utilisateurRepository.findAll();
        return utilisateurs.stream()
                .map(u->modelMapper.map(u, UtilisateurDto.class))
                .collect(Collectors.toList());
      }
      public UtilisateurDto updateUtilisateur( Long id,UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur=utilisateurRepository.findById(id).get()  ;
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setRole(utilisateurDto.getRole());
        Utilisateur Updated=utilisateurRepository.save(utilisateur);
        return modelMapper.map(Updated, UtilisateurDto.class);

      }
      public UtilisateurDto getUtilisateur(Long id) {
        Utilisateur utilisateur=utilisateurRepository.findById(id).get()  ;
        return modelMapper.map(utilisateur, UtilisateurDto.class);

      }
}
