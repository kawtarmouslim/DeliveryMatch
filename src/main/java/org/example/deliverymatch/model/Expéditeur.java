package org.example.deliverymatch.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Expéditeur extends Utilisateur{
    @OneToMany(mappedBy = "expediteur")
    private List<Demande> demandes;
}
