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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.activity.adapters.AdapterNiveau;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class SelectionNiveauActivity extends AppCompatActivity{

    RecyclerView recyclerView = null;
    ProgressBar progressBar = null;
    CategorieViewModel categorieVm;
    MotViewModel motVM;
    AdapterNiveau adapterNiveau;
    List<Integer> progression = new ArrayList<>();
    int pourcentageProgression = 0;
    int nbBonnesReponses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);

        progressBar = findViewById(R.id.pb_progression);


        motVM = ViewModelProviders.of(this).get(MotViewModel.class);

        int idCat=0;
        while (idCat<=7){
            idCat++;
            motVM.getMotByCategorie(idCat);
            Log.i("Devinet", "idCat : " + idCat);
        }
        motVM.getObservateurMotByCategorie().observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                if(mots.isEmpty())
                    return;
                pourcentageProgression = 0;
                nbBonnesReponses = 0;
                for (Mot mot:mots) {
                    if(mot.getMot().equalsIgnoreCase(mot.getProposition())) {
                        nbBonnesReponses++;
                    }
                }
                Log.i("Devinet", "nbBonnesReponses : " + nbBonnesReponses);
                pourcentageProgression = (nbBonnesReponses*100)/mots.size();
                progression.add(pourcentageProgression);
                Log.i("Devinet", "pourcentageProgression : " + pourcentageProgression);
            }
        });


        //récupérer le recycleview
        recyclerView = findViewById(R.id.recyclerview_niveau);
        //optimise le chargement quand le rv ne change pas de taille
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlm);

        //Récupérer la liste de catégorie
        categorieVm = ViewModelProviders.of(this).get(CategorieViewModel.class);


        categorieVm.getAllCategories().observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                //création de l'adapter
                adapterNiveau = new AdapterNiveau(categories, new AdapterNiveau.OnClicSurItem<Categorie>() {
                    @Override
                    public void onInteraction(Categorie info) {
                        Log.i("Devinet", "Catégorie : " + info);
                        Intent intentJouer = new Intent(SelectionNiveauActivity.this, DevinerMotActivity.class);
                        intentJouer.putExtra("Categorie", info);
                        startActivity(intentJouer);
                    }
                }, progression);

                //lié l'adapter au recycleview
                recyclerView.setAdapter(adapterNiveau);

                adapterNiveau.setInterfaceClickNiveau(new AdapterNiveau.IOnClickNiveau() {
                    @Override
                    public void onClickReinitialise(Categorie categorie) {
                        //récupérer les mots de la catégorie
                        motVM.getMotByCategorie(categorie.getId());
                        motVM.getObservateurMotByCategorie().observe(SelectionNiveauActivity.this, new Observer<List<Mot>>() {
                            @Override
                            public void onChanged(List<Mot> mots) {
                                //mettre les mots proposés à null
                                for (Mot mot: mots) {
                                    mot.setProposition("");
                                    //insérer dans la bdd
                                    motVM.update(mot);
                                }
                                Log.i("Devinet", "mot mis à jour : '" + mots.size()+"' ");
                            }
                        });
                        Toast.makeText(SelectionNiveauActivity.this, "Le niveau "+ categorie.getId() +" a été réinitialisé. ", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
                });
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