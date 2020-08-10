package com.example.lsddevinet.dal;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

@Database(entities = {Mot.class, Categorie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    //pattern singleton
    private static AppDatabase INSTANCE_CNX = null;

    //Permet de fournir une instance de la dao utilisateur aux couches supérieures.
    public abstract CategorieDao getCategorieDao();
    public abstract MotDao getMotDao();

    public static AppDatabase getInstance(Context context){

        if (INSTANCE_CNX == null){
            INSTANCE_CNX = Room.databaseBuilder(context, AppDatabase.class, "devinet.db")
                    .addCallback(roomFixture)
                    .build();
        }
        return INSTANCE_CNX;
    }

    /**
     * Méthode pour implémenter dans la bdd des exemples de mots
     */
    private static Callback roomFixture = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new AsyncTask<AppDatabase,Void,Void>(){

                @Override
                protected Void doInBackground(AppDatabase... appDatabases) {
                    return null;
                }
            }.execute(INSTANCE_CNX);
        }
    };
}
