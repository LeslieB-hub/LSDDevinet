package com.example.lsddevinet.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MotAvecCategorie {

    @Embedded
    public Categorie categorie;
    @Relation(
            parentColumn = "id",
            entityColumn = "idCategorie"
    )
    public List<Mot> mots;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Mot> getMots() {
        return mots;
    }

    public void setMots(List<Mot> mots) {
        this.mots = mots;
    }
}
