package com.example.lsddevinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;
import com.example.lsddevinet.repository.CategorieBddRepository;
import com.example.lsddevinet.repository.ICategorieRepository;
import com.example.lsddevinet.repository.IMotBddRepository;
import com.example.lsddevinet.repository.MotBddRepository;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);

//        ICategorieRepository repoCategorie = new CategorieBddRepository(this);
//        IMotBddRepository repoMot = new MotBddRepository(this);
//        Categorie categorie = new Categorie(4,"4 lettres");
//        repoCategorie.insert(categorie);
//        repoMot.insert(new Mot(0, "kiwi.jpeg", "kiwi", "", 4 ));
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