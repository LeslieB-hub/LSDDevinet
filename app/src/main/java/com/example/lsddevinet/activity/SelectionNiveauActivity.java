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
import com.example.lsddevinet.activity.adapters.AdapterNiveau;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class SelectionNiveauActivity extends AppCompatActivity {

    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);
        recyclerView = findViewById(R.id.recyclerview_niveau);
    }

    @Override
    protected void onResume() {
        super.onResume();

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlm);

        //Récupérer la liste de catégorie
        CategorieViewModel categorieVm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        LiveData<List<Categorie>> observateur = categorieVm.getAllCategories();

        observateur.observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                AdapterNiveau adapterNiveau = new AdapterNiveau(categories);
                recyclerView.setAdapter(adapterNiveau);
            }
        });

    }

    public void onClickInitialiser(){

    }

    public void onClickProgression(View view){
        View parentRow = (View) view.getParent();

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
//            case R.id.action_settings:
//                Intent intentParametre = new Intent(this, .class);
//                startActivity(intentParametre);
//                return true;
//            case R.id.action_APropos:
//                Intent intentAPropos = new Intent(this, .class);
//                startActivity(intentAPropos);
//                return true;
            case R.id.action_PagePrecedente:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}