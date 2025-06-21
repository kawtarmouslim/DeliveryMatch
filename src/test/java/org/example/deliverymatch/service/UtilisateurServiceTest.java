package org.example.deliverymatch.service;

import org.example.deliverymatch.dto.UtilisateurDto;
import org.example.deliverymatch.model.Role;
import org.example.deliverymatch.model.Utilisateur;
import org.example.deliverymatch.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtilisateurServiceTest {
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UtilisateurService utilisateurService;

    private Utilisateur utilisateur;
    private UtilisateurDto utilisateurDto;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("Kawtar");
        utilisateur.setEmail("kawtar@example.com");
        utilisateur.setPassword("123456");
        utilisateur.setRole(Role.CONDUCTEUR);

        utilisateurDto = new UtilisateurDto();
        utilisateurDto.setNom("Kawtar");
        utilisateurDto.setEmail("kawtar@example.com");
        utilisateurDto.setPassword("123456");
        utilisateurDto.setRole(Role.CONDUCTEUR);
    }

    @Test
    void testGetAllUtilisateurs() {
        List<Utilisateur> utilisateurs = Arrays.asList(utilisateur);
        when(utilisateurRepository.findAll()).thenReturn(utilisateurs);
        when(modelMapper.map(utilisateur, UtilisateurDto.class)).thenReturn(utilisateurDto);

        List<UtilisateurDto> result = utilisateurService.getAllUtilisateurs();

        assertEquals(1, result.size());
        assertEquals("Kawtar", result.get(0).getNom());
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testGetUtilisateurById() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(modelMapper.map(utilisateur, UtilisateurDto.class)).thenReturn(utilisateurDto);

        UtilisateurDto result = utilisateurService.getUtilisateur(1L);

        assertNotNull(result);
        assertEquals("Kawtar", result.getNom());
        verify(utilisateurRepository).findById(1L);
    }

    @Test
    void testUpdateUtilisateur() {
        Utilisateur updatedUtilisateur = new Utilisateur();
        updatedUtilisateur.setId(1L);
        updatedUtilisateur.setNom("Nouvelle Kawtar");
        updatedUtilisateur.setEmail("new@example.com");
        updatedUtilisateur.setPassword("newpass");
        updatedUtilisateur.setRole(Role.EXPEDITEUR);

        UtilisateurDto updatedDto = new UtilisateurDto();
        updatedDto.setNom("Nouvelle Kawtar");
        updatedDto.setEmail("new@example.com");
        updatedDto.setPassword("newpass");
        updatedDto.setRole(Role.CONDUCTEUR);
        ;

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(updatedUtilisateur);
        when(modelMapper.map(updatedUtilisateur, UtilisateurDto.class)).thenReturn(updatedDto);

        UtilisateurDto result = utilisateurService.updateUtilisateur(1L, updatedDto);

        assertEquals("Nouvelle Kawtar", result.getNom());
        assertEquals("new@example.com", result.getEmail());
        verify(utilisateurRepository).save(any(Utilisateur.class));
    }

}