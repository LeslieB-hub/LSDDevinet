package com.example.lsddevinet.repository;

import androidx.lifecycle.LiveData;

import com.example.lsddevinet.model.Categorie;

import java.util.List;

public interface ICategorieRepository {
    void insert(Categorie categorie);
    LiveData<List<Categorie>> getAllCategories();
    Categorie getCategory(int id);
    void update(Categorie categorie);
    void delete(Categorie categorie);
}
