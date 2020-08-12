package com.example.lsddevinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.activity.adapters.AdapterNiveau;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class SelectionNiveauActivity extends AppCompatActivity{

    RecyclerView recyclerView = null;
    ProgressBar progressBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);

        progressBar = findViewById(R.id.pb_progression);

        //récupérer le recycleview
        recyclerView = findViewById(R.id.recyclerview_niveau);
        //optimise le chargement quand le rv ne change pas de taille
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlm);

        //Récupérer la liste de catégorie
        CategorieViewModel categorieVm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        LiveData<List<Categorie>> observateur = categorieVm.getAllCategories();

        observateur.observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                //création de l'adapter
                AdapterNiveau adapterNiveau = new AdapterNiveau(categories, new AdapterNiveau.OnClicSurItem<Categorie>() {
                    @Override
                    public void onInteraction(Categorie info) {
                        Toast.makeText(SelectionNiveauActivity.this, "Catégorie"+ info, Toast.LENGTH_SHORT).show();
                        Intent intentJouer = new Intent(SelectionNiveauActivity.this, DevinerMotActivity.class);
                        intentJouer.putExtra("Categorie", info);
                        startActivity(intentJouer);
                    }
                });

                //lié l'adapter au recycleview
                recyclerView.setAdapter(adapterNiveau);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();




    }

    public void onClickInitialiser(){

    }

/*    public void onClickProgression(CardView view){
        View parentRow = (View) view.getParent();
        RecyclerView rv = (RecyclerView) parentRow.getParent();
        final int position = rv.getChildLayoutPosition(parentRow);
        Toast.makeText(SelectionNiveauActivity.this," d "+ position, Toast.LENGTH_LONG).show();
    }*/

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
//            case R.id.action_settings:
//                Intent intentParametre = new Intent(this, .class);
//                startActivity(intentParametre);
//                return true;
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