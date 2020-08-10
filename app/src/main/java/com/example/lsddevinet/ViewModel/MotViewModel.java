package com.example.lsddevinet.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lsddevinet.model.Mot;
import com.example.lsddevinet.repository.IMotBddRepository;
import com.example.lsddevinet.repository.MotBddRepository;

import java.util.List;

public class MotViewModel extends AndroidViewModel {

    IMotBddRepository motRepo;


    public MotViewModel(@NonNull Application application) {
        super(application);
        motRepo = new MotBddRepository(application);
    }

    public LiveData<List<Mot>> getAllMots() {
        return motRepo.getAllMots();
    }

    public LiveData<List<Mot>> getMotByCategorie(int idCategorie) {
        return motRepo.getMotByCategorie(idCategorie);
    }


    public void insert(final Mot mot) {
        motRepo.insert(mot);
    }
    public void get(int id) {
        motRepo.get(id);
    }
    public void update(final Mot mot) {
        motRepo.update(mot);
    }
    public void delete(final Mot mot) {
        motRepo.delete(mot);
    }

}
