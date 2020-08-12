package com.example.lsddevinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.activity.adapters.AdapterNiveau;
import com.example.lsddevinet.activity.adapters.ResultatsAdapter;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class MesResultatsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter resultatsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_resultats);

        recyclerView = findViewById(R.id.recycler_view_resultats);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



/////////////////////////////////////////
        //Récupérer la liste de catégorie
        CategorieViewModel categorieVm = ViewModelProviders.of(this).get(CategorieViewModel.class);
        LiveData<List<Categorie>> observateur = categorieVm.getAllCategories();

        observateur.observe(this, new Observer<List<Categorie>>() {
            @Override
            public void onChanged(List<Categorie> categories) {
                ResultatsAdapter resultatsAdapter = new ResultatsAdapter(categories);
            }
            });
        //lié l'adapter au recycleview
        recyclerView.setAdapter(resultatsAdapter);
    }

    public void OnClickDetails(View view) {
        Intent intentDetails = new Intent(this, DetailsResultatsActivity.class);
        startActivity(intentDetails);
    }

}