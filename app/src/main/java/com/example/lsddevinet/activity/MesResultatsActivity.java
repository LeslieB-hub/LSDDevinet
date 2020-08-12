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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.activity.adapters.ResultatsAdapter;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class MesResultatsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter resultatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_resultats);

        recyclerView = findViewById(R.id.recycler_view_resultats);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Récupérer la liste de catégorie
        CategorieViewModel categorieVm = ViewModelProviders.of(this).get(CategorieViewModel.class);

        categorieVm.getAllCategories().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                resultatsAdapter = new ResultatsAdapter(categories);
                //lié l'adapter au recycleview
                recyclerView.setAdapter(resultatsAdapter);
            }
            });


    }

    public void OnClickDetails(View view) {
        Intent intentDetails = new Intent(this, DetailResultatActivity.class);
        startActivity(intentDetails);
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
            case R.id.action_PagePrecedente:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}