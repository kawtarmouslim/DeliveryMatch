package org.example.deliverymatch.service;

import org.example.deliverymatch.dto.AnnonceDto;
import org.example.deliverymatch.model.Annonce;
import org.example.deliverymatch.model.Conducteur;
import org.example.deliverymatch.repository.AnnonceRepository;
import org.example.deliverymatch.repository.UtilisateurRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)

class AnnonceServiceTest {
    @Mock
    private AnnonceRepository annonceRepository;
    @Mock private UtilisateurRepository utilisateurRepository;
    @Mock private ModelMapper modelMapper;

    @InjectMocks
    private AnnonceService annonceService;

    private Conducteur conducteur;
    private Annonce annonce;
    private AnnonceDto annonceDto;

    @BeforeEach
    void setUp() {
        conducteur = new Conducteur();
        conducteur.setId(1L);
        conducteur.setNom("Kawtar");
        conducteur.setEmail("kawtar@example.com");

        annonce = new Annonce();
        annonce.setIdAnnonce(1L);
        annonce.setDepart("Casablanca");
        annonce.setDestination("Rabat");
        annonce.setTypeColis("Documents");
        annonce.setConducteur(conducteur);

        annonceDto = new AnnonceDto();
        annonceDto.setIdAnnonce(1L);
        annonceDto.setDepart("Casablanca");
        annonceDto.setDestination("Rabat");
        annonceDto.setTypeColis("Documents");
    }

    @Test
    void publierAnnonce_shouldSaveAnnonceWithCurrentConducteur() {
        // Simuler l'utilisateur connecté
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("kawtar@example.com");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(utilisateurRepository.findByEmail("kawtar@example.com")).thenReturn(Optional.of(conducteur));
        when(annonceRepository.save(any(Annonce.class))).thenReturn(annonce);
        when(modelMapper.map(annonce, AnnonceDto.class)).thenReturn(annonceDto);

        AnnonceDto result = annonceService.publierAnnonce(annonceDto);

        assertNotNull(result);
        assertEquals("Rabat", result.getDestination());
        verify(annonceRepository).save(any(Annonce.class));
    }

    @Test
    void getAnnonces_shouldReturnAllMappedDtos() {
        List<Annonce> annonces = List.of(annonce);
        when(annonceRepository.findAll()).thenReturn(annonces);
        when(modelMapper.map(annonce, AnnonceDto.class)).thenReturn(annonceDto);

        List<AnnonceDto> result = annonceService.getAnnonces();

        assertEquals(1, result.size());
        assertEquals("Rabat", result.get(0).getDestination());
    }

    @Test
    void updateAnnonce_shouldModifyAndReturnUpdatedAnnonce() {
        when(annonceRepository.findById(1L)).thenReturn(Optional.of(annonce));
        when(annonceRepository.save(any(Annonce.class))).thenReturn(annonce);
        when(modelMapper.map(annonce, AnnonceDto.class)).thenReturn(annonceDto);

        AnnonceDto updated = annonceService.updateAnnonce(1L, annonceDto);

        assertEquals("Rabat", updated.getDestination());
        verify(annonceRepository).save(any(Annonce.class));
    }

    @Test
    void deleteAnnonce_shouldCallRepository() {
        annonceService.deleteAnnonce(1L);
        verify(annonceRepository).deleteById(1L);
    }

    @Test
    void searchAnnonces_shouldReturnFilteredAnnonces() {
        when(annonceRepository.searchAnnonces("Rabat", "Documents")).thenReturn(List.of(annonce));
        when(modelMapper.map(annonce, AnnonceDto.class)).thenReturn(annonceDto);

        List<AnnonceDto> result = annonceService.searchAnnonces("Rabat", "Documents");

        assertEquals(1, result.size());
        assertEquals("Documents", result.get(0).getTypeColis());
    }

}