package com.example.lsddevinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.activity.adapters.AdapterResultatDetail;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;
import com.example.lsddevinet.repository.CategorieBddRepository;
import com.example.lsddevinet.repository.ICategorieRepository;
import com.example.lsddevinet.repository.IMotBddRepository;
import com.example.lsddevinet.repository.MotBddRepository;
import com.facebook.stetho.Stetho;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String CLE_PROGRESSION = "progression";
    public static final String FICHIER_PROGRESSION_TOTALE = "progression_totale";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);



        ///CALCULER LA PROGESSION TOTALE ET METTRE DANS SHARE PREFERENCES
        motVM.getObservateurAllMots().observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                int size = mots.size();

                int idCat = 0;
                int nbBonnesReponses = 0;
                int progression = 0;

                for(Mot mot: mots){
                    if(mot.getMot().equalsIgnoreCase(mot.getProposition())) {
                            nbBonnesReponses++;
                    }
                }

                    progression = (nbBonnesReponses*100)/size;
                    SharedPreferences sp = getSharedPreferences(FICHIER_PROGRESSION_TOTALE, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt(CLE_PROGRESSION, progression);
                    editor.commit();


            }
        });

    }


    public void onClickJouer(View view) {
        Intent intentNiveau = new Intent(this, SelectionNiveauActivity.class);
        startActivity(intentNiveau);
    }


    public void onClickResultat(View view) {
        Intent intentResultats = new Intent(this, MesResultatsActivity.class);
        startActivity(intentResultats);
    }

    public void onClickProposition(View view) {
    }

    public void onClickQuitter(View view) {
    }

    /**
     * Menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //on décompresse le xml du menu
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        menu.findItem(R.id.action_Accueil).setVisible(false);
        menu.findItem(R.id.action_PagePrecedente).setVisible(false);
        return true;
    }


    /**
     * Définition des actions
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intentParametre = new Intent(this, ParametreActivity.class);
                startActivity(intentParametre);
                return true;
            case R.id.action_APropos:
                Intent intentAPropos = new Intent(this, AProposActivity.class);
                startActivity(intentAPropos);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}