package com.example.lsddevinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.activity.adapters.ResultatsAdapter;
import com.example.lsddevinet.model.Categorie;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MesResultatsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter resultatsAdapter;
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList pieEntryLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_resultats);

        pieChart = findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
        pieDataSet.setSliceSpace(5f);


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

    //Pour definir les valeurs de camambert : progression totale
    private void getEntries() {

        int progressionTotale;
        int motsPasTrouve;
        SharedPreferences sp = getSharedPreferences(MainActivity.FICHIER_PROGRESSION_TOTALE, MODE_PRIVATE);
        progressionTotale = sp.getInt(MainActivity.CLE_PROGRESSION, 0);
        motsPasTrouve = 100 - progressionTotale;

        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(progressionTotale, "Mot trouvé"));
        pieEntries.add(new PieEntry(motsPasTrouve, "Mot non trouvé"));

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