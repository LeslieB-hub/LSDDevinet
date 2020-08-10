package com.example.lsddevinet.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.lsddevinet.model.Mot;
import com.example.lsddevinet.model.MotAvecCategorie;

import java.util.List;

@Dao
public interface MotDao {

    @Insert
    void insert(Mot mot);

    /**
     * Selectionne
     * @return tous les mots de la bdd
     */
    @Transaction
    @Query("SELECT * FROM Mot")
    LiveData<List<Mot>> getAllMots();


    /**
     * Selectionne
     * @param idCategorie
     * @return tous les mots de la bdd d'une catégorie
     */
    @Query("SELECT * FROM Mot WHERE idCategorie = :idCategorie")
    LiveData<List<Mot>> getMotByCategorie(int idCategorie);

    /**
     * Selectionne un mot
     * @param id
     * @return un mot
     */
    //@Query("SELECT Mot.id, Mot.img, Mot.mot, Mot.proposition, Cat.categorie FROM Mot JOIN Categorie as Cat ON Mot.id_categorie = Cat.id")
    @Query("SELECT * FROM Mot WHERE id = :id")
    Mot get(int id);


    /**
     * Mettre à jour un mot
     * @param mot
     */
    @Update
    void update(Mot mot);

    /**
     * Supprimer un mot
     * @param mot
     */
    @Delete
    void delete(Mot mot);


}
