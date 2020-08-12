package com.example.lsddevinet.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.lsddevinet.dal.AppDatabase;
import com.example.lsddevinet.dal.CategorieDao;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class CategorieBddRepository implements ICategorieRepository {

    private CategorieDao categorieDao;
    private LiveData<List<Categorie>> observateurCategories;
    Categorie categorieGet = null;



    /**
     * Constructeur pour récupérer le context de l'appli et se connecter à la bdd une seule fois
     * @param context
     */
    public CategorieBddRepository(Context context){
        //Instance de la bdd
        AppDatabase database = AppDatabase.getInstance(context);
        //Instance de la dao Catégorie et Mot
        categorieDao = database.getCategorieDao();
        observateurCategories = categorieDao.getAllCategories();
    }

    @Override
    public void insert(Categorie categorie) {
        new AsyncTask<Categorie,Void,Void>(){

            @Override
            protected Void doInBackground(Categorie... categories) {
                categorieDao.insert(categories[0]);
                return null;
            }
        }.execute(categorie);
    }

    @Override
    public LiveData<List<Categorie>> getAllCategories() {
        return observateurCategories;
    }

    @Override
    public Categorie getCategory(int id) {
        new AsyncTask<Integer,Void,Categorie>(){

            @Override
            protected Categorie doInBackground(Integer... integers) {
                return categorieDao.getCategory(integers[0]);
            }

            @Override
            protected void onPostExecute(Categorie categorie) {
                super.onPostExecute(categorie);
                categorieGet = categorie;
            }
        }.execute();
        return categorieGet;
    }

    @Override
    public void update(Categorie categorie) {
        new AsyncTask<Categorie,Void,Void>(){

            @Override
            protected Void doInBackground(Categorie... categories) {
                categorieDao.update(categories[0]);
                return null;
            }
        }.execute(categorie);

    }

    @Override
    public void delete(Categorie categorie) {
        new AsyncTask<Categorie,Void,Void>(){

            @Override
            protected Void doInBackground(Categorie... categories) {
                categorieDao.delete(categories[0]);
                return null;
            }
        }.execute(categorie);
    }
}
