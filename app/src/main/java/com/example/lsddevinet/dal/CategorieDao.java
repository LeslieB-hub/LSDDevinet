package com.example.lsddevinet.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lsddevinet.model.Categorie;

import java.util.List;

@Dao
public interface CategorieDao {
    @Insert
    void insert(Categorie categorie);

    @Query("SELECT * FROM Categorie")
    LiveData<List<Categorie>> getAllCategories();

    @Query("SELECT * FROM Categorie WHERE id = :id")
    Categorie getCategory(int id);

    @Update
    void update(Categorie categorie);
    
    @Delete
    void delete(Categorie categorie);
}
