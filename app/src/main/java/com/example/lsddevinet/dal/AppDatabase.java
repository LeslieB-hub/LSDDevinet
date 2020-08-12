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
                    .fallbackToDestructiveMigration()
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

                    motDao.insert(new Mot("kiwi.jpeg", "kiwi", "", 1 ));
                    motDao.insert(new Mot("tank.jpeg", "tank", "tank", 1 ));
                    motDao.insert(new Mot("chat.jpeg", "chat", "", 1 ));
                    motDao.insert(new Mot("main.jpeg", "main", "", 1 ));
                    motDao.insert(new Mot("pain.jpeg", "pain", "pain", 1 ));
                    motDao.insert(new Mot("oeil.jpeg", "oeil", "oeil", 1 ));

                    motDao.insert(new Mot("chien.jpeg", "chien", "", 2 ));
                    motDao.insert(new Mot("pomme.jpeg", "pomme", "", 2 ));
                    motDao.insert(new Mot("raisin.jpeg", "raisin", "", 2 ));
                    motDao.insert(new Mot("stylo.jpeg", "stylo", "", 2 ));
                    motDao.insert(new Mot("herbe.jpeg", "herbe", "", 2 ));
                    motDao.insert(new Mot("livre.jpeg", "livre", "livre", 2 ));

                    motDao.insert(new Mot("chaise.jpeg", "chaise", "chaise", 3));
                    motDao.insert(new Mot("dragon.jpeg", "dragon", "dragon", 3 ));
                    motDao.insert(new Mot("soleil.jpeg", "soleil", "soleil", 3 ));
                    motDao.insert(new Mot("fraise.jpeg", "fraise", "", 3 ));
                    motDao.insert(new Mot("miroir.jpeg", "miroir", "", 3 ));
                    motDao.insert(new Mot("bateau.jpeg", "bateau", "", 3 ));

                    motDao.insert(new Mot("abricot.jpeg", "abricot", "", 4 ));
                    motDao.insert(new Mot("abeille.jpeg", "abeille", "", 4 ));
                    motDao.insert(new Mot("ananas.jpeg", "ananas", "", 4 ));
                    motDao.insert(new Mot("poisson.jpeg", "poisson", "", 4 ));
                    motDao.insert(new Mot("carotte.jpeg", "carotte", "", 4 ));

                    motDao.insert(new Mot("couronne.jpeg", "couronne", "", 5 ));
                    motDao.insert(new Mot("pastèque.jpeg", "pastèque", "", 5 ));
                    motDao.insert(new Mot("papillon.jpeg", "papillon", "", 5 ));

                    motDao.insert(new Mot("enveloppe.jpeg", "enveloppe", "", 6 ));
                    motDao.insert(new Mot("coccinelle.jpeg", "coccinelle", "", 6 ));
                    motDao.insert(new Mot("chaussure.jpeg", "chaussure", "", 6 ));
                    motDao.insert(new Mot("autoroute.jpeg", "autoroute", "", 6 ));
                    motDao.insert(new Mot("cigarette.jpeg", "cigarette", "", 6 ));


                    motDao.insert(new Mot("grenouille.jpeg", "grenouille", "grenouille", 7 ));
                    motDao.insert(new Mot("champignon.jpeg", "champignon", "champignon", 7 ));

                    return null;
                }
            }.execute(INSTANCE_CNX);

        }
    };
}
