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

@Database(entities = {Mot.class, Categorie.class}, exportSchema = false, version = 1)
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
                    MotDao motDao = INSTANCE_CNX.getMotDao();
                    CategorieDao categorieDao = INSTANCE_CNX.getCategorieDao();
                    categorieDao.insert(new Categorie(1,"4 lettres"));
                    categorieDao.insert(new Categorie(2,"5 lettres"));
                    categorieDao.insert(new Categorie(3,"6 lettres"));
                    categorieDao.insert(new Categorie(4,"7 lettres"));
                    categorieDao.insert(new Categorie(5,"8 lettres"));
                    categorieDao.insert(new Categorie(6,"9 lettres"));
                    categorieDao.insert(new Categorie(7,"10 lettres"));

                    motDao.insert(new Mot(0, "kiwi.jpeg", "kiwi", "", 1 ));
                    motDao.insert(new Mot(0, "tank.jpeg", "tank", "tank", 1 ));
                    motDao.insert(new Mot(0, "chat.jpeg", "chat", "", 1 ));
                    motDao.insert(new Mot(0, "main.jpeg", "main", "", 1 ));
                    motDao.insert(new Mot(0, "pain.jpeg", "pain", "pain", 1 ));
                    motDao.insert(new Mot(0, "oeil.jpeg", "oeil", "oeil", 1 ));

                    motDao.insert(new Mot(0, "chien.jpeg", "chien", "", 2 ));
                    motDao.insert(new Mot(0, "pomme.jpeg", "pomme", "", 2 ));
                    motDao.insert(new Mot(0, "raisin.jpeg", "raisin", "", 2 ));
                    motDao.insert(new Mot(0, "stylo.jpeg", "stylo", "", 2 ));
                    motDao.insert(new Mot(0, "herbe.jpeg", "herbe", "", 2 ));
                    motDao.insert(new Mot(0, "livre.jpeg", "livre", "livre", 2 ));

                    motDao.insert(new Mot(0, "chaise.jpeg", "chaise", "chaise", 3));
                    motDao.insert(new Mot(0, "dragon.jpeg", "dragon", "dragon", 3 ));
                    motDao.insert(new Mot(0, "soleil.jpeg", "soleil", "soleil", 3 ));
                    motDao.insert(new Mot(0, "fraise.jpeg", "fraise", "", 3 ));
                    motDao.insert(new Mot(0, "miroir.jpeg", "miroir", "", 3 ));
                    motDao.insert(new Mot(0, "bateau.jpeg", "bateau", "", 3 ));

                    motDao.insert(new Mot(0, "abricot.jpeg", "abricot", "", 4 ));
                    motDao.insert(new Mot(0, "abeille.jpeg", "abeille", "", 4 ));
                    motDao.insert(new Mot(0, "ananas.jpeg", "ananas", "", 4 ));
                    motDao.insert(new Mot(0, "poisson.jpeg", "poisson", "", 4 ));
                    motDao.insert(new Mot(0, "carotte.jpeg", "carotte", "", 4 ));

                    motDao.insert(new Mot(0, "couronne.jpeg", "couronne", "", 5 ));
                    motDao.insert(new Mot(0, "pastèque.jpeg", "pastèque", "", 5 ));
                    motDao.insert(new Mot(0, "papillon.jpeg", "papillon", "", 5 ));

                    motDao.insert(new Mot(0, "enveloppe.jpeg", "enveloppe", "", 6 ));
                    motDao.insert(new Mot(0, "coccinelle.jpeg", "coccinelle", "", 6 ));
                    motDao.insert(new Mot(0, "chaussure.jpeg", "chaussure", "", 6 ));
                    motDao.insert(new Mot(0, "autoroute.jpeg", "autoroute", "", 6 ));
                    motDao.insert(new Mot(0, "cigarette.jpeg", "cigarette", "", 6 ));


                    motDao.insert(new Mot(0, "grenouille.jpeg", "grenouille", "grenouille", 7 ));
                    motDao.insert(new Mot(0, "champignon.jpeg", "champignon", "champignon", 7 ));

                    return null;
                }
            }.execute(INSTANCE_CNX);

        }
    };
}
