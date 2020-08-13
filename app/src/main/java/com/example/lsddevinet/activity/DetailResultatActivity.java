package com.example.lsddevinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.activity.adapters.AdapterResultatDetail;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

import java.util.List;

public class DetailResultatActivity extends AppCompatActivity {

    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resultat);

        //récupérer le recycleview
        recyclerView = findViewById(R.id.recyclerview_resultat);
        //optimise le chargement quand le rv ne change pas de taille
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlm);

        //Récupérer la liste entière de mots
        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);

        motVM.getObservateurAllMots().observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                AdapterResultatDetail adapterResultatDetail = new AdapterResultatDetail(mots);
                recyclerView.setAdapter(adapterResultatDetail);
            }

        });
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
        return true;
    }

    /**
     * Définition des actions
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.action_Accueil:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                return true;
            case R.id.action_settings:
                Intent intentParametre = new Intent(this, ParametreActivity.class);
                startActivity(intentParametre);
                return true;
            case R.id.action_APropos:
                Intent intentAPropos = new Intent(this, AProposActivity.class);
                startActivity(intentAPropos);
                return true;
            case R.id.action_PagePrecedente:
                Intent intentResultat = new Intent(this, MesResultatsActivity.class);
                startActivity(intentResultat);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}