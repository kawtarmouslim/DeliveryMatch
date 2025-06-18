package org.example.deliverymatch.model;

import jakarta.persistence.*;

import java.util.List;
@Entity

public class Conducteur extends Utilisateur{



    @OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private List<Annonce> annonces;
}
