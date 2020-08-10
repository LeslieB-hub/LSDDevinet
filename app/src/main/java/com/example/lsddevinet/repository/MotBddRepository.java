package com.example.lsddevinet.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.lsddevinet.dal.AppDatabase;
import com.example.lsddevinet.dal.CategorieDao;
import com.example.lsddevinet.dal.MotDao;
import com.example.lsddevinet.model.Mot;
import com.example.lsddevinet.model.MotAvecCategorie;

import java.util.List;

public class MotBddRepository implements IMotBddRepository {

    private MotDao motDao = null;
    Mot motGet = null;

    /**
     * Constructeur pour récupérer le context de l'appli et se connecter à la bdd une seule fois
     * @param context
     */
    public MotBddRepository(Context context){
        //Instance de la bdd
        AppDatabase database = AppDatabase.getInstance(context);
        //Instance de la dao Catégorie et Mot
        motDao = database.getMotDao();
    }

    @Override
    public void insert(Mot mot) {
        new AsyncTask<Mot,Void,Void>(){

            @Override
            protected Void doInBackground(Mot... mots) {
                motDao.insert(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public LiveData<List<MotAvecCategorie>> getAllMots() {
        return motDao.getAllMots();
    }

    @Override
    public LiveData<List<Mot>> getMotByCategorie(int idCategorie) {
        return motDao.getMotByCategorie(idCategorie);
    }

    @Override
    public Mot get(int id) {
        new AsyncTask<Integer,Void,Mot>(){

            @Override
            protected Mot doInBackground(Integer... integers) {
                return motDao.get(integers[0]);
            }

            @Override
            protected void onPostExecute(Mot mot) {
                super.onPostExecute(mot);
                motGet = mot;
            }
        }.execute();
        return motGet;
    }

    @Override
    public void update(Mot mot) {
        new AsyncTask<Mot,Void,Void>(){

            @Override
            protected Void doInBackground(Mot... mots) {
                motDao.update(mots[0]);
                return null;
            }
        }.execute(mot);
    }

    @Override
    public void delete(Mot mot) {
        new AsyncTask<Mot,Void,Void>(){

            @Override
            protected Void doInBackground(Mot... mots) {
                motDao.delete(mots[0]);
                return null;
            }
        }.execute(mot);
    }
}
