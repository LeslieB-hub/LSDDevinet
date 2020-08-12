package com.example.lsddevinet.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lsddevinet.dal.AppDatabase;
import com.example.lsddevinet.dal.MotDao;
import com.example.lsddevinet.model.Mot;

import java.util.List;

public class MotBddRepository implements IMotBddRepository {

    private MotDao motDao;
    Mot motGet = null;
    LiveData<List<Mot>> observateurgetAllMots = null;
    MutableLiveData<List<Mot>> observateurgetMotsByCategorie = null;


    /**
     * Constructeur pour récupérer le context de l'appli et se connecter à la bdd une seule fois
     * @param context
     */
    public MotBddRepository(Context context){
        //Instance de la bdd
        AppDatabase database = AppDatabase.getInstance(context);
        //Instance de la dao Catégorie et Mot
        motDao = database.getMotDao();

        observateurgetAllMots= motDao.getAllMots();

        observateurgetMotsByCategorie = new MutableLiveData<>();

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
    public LiveData<List<Mot>> getObservateurAllMots() {
        return observateurgetAllMots;
    }

    @Override
    public LiveData<List<Mot>> getObservateurMotByCategorie() {
        return observateurgetMotsByCategorie;
    }


    @Override
    public void getMotByCategorie(int idCategorie) {
        new AsyncTask<Integer,Void,List<Mot>>(){

            @Override
            protected List<Mot> doInBackground(Integer... integers) {
                return motDao.getMotByCategorie(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Mot> mots) {
                super.onPostExecute(mots);
                observateurgetMotsByCategorie.setValue(mots);
            }
        }.execute(idCategorie);
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
