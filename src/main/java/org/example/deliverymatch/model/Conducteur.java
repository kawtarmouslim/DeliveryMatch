package org.example.deliverymatch.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@DiscriminatorValue("conducteur")

public class Conducteur extends Utilisateur{

@Id
private Long id;
    @OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private List<Annonce> annonces;
}
