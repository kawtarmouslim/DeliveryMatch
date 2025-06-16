package org.example.deliverymatch.dto;

import lombok.*;
import org.example.deliverymatch.model.Role;
import org.example.deliverymatch.model.Utilisateur;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UtilisateurDto implements Serializable {
    Long id;
    String nom;
    String email;
    String password;
    Role role;
}