package com.example.lsddevinet.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;
import com.example.lsddevinet.repository.CategorieBddRepository;
import com.example.lsddevinet.repository.ICategorieRepository;
import com.example.lsddevinet.repository.IMotBddRepository;
import com.example.lsddevinet.repository.MotBddRepository;

import java.util.List;

public class CategorieViewModel extends AndroidViewModel {

    ICategorieRepository categRepo;



    public CategorieViewModel(@NonNull Application application) {
        super(application);
        categRepo = new CategorieBddRepository(application);
    }

    public LiveData<List<Categorie>> getAllCategories() {
        return categRepo.getAllCategories();
    }

    public void insert(final Categorie categorie) {

        categRepo.insert(categorie);
    }
    public void getCategory(int id) {

        categRepo.getCategory(id);
    }
    public void update(final Categorie categorie) {

        categRepo.update(categorie);
    }
    public void delete(final Categorie categorie) {

        categRepo.delete(categorie);
    }

}




