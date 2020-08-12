package com.example.lsddevinet.repository;

import androidx.lifecycle.LiveData;

import com.example.lsddevinet.model.Mot;

import java.util.List;

public interface IMotBddRepository {

    void insert(Mot mot);
    LiveData<List<Mot>> getObservateurAllMots();
    LiveData<List<Mot>> getObservateurMotByCategorie();
    void getMotByCategorie(int idCategorie);
    Mot get(int id);
    void update(Mot mot);
    void delete(Mot mot);
}
