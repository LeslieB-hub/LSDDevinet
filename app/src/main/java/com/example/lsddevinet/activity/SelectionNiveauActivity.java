package com.example.lsddevinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lsddevinet.R;
import com.example.lsddevinet.activity.adapters.AdapterNiveau;

public class SelectionNiveauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_niveau);
    }

    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_niveau);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlm);

        String[] infos = {"Bleu","Blanc","Rouge"};

        AdapterNiveau adapterNiveau = new AdapterNiveau(infos);
        recyclerView.setAdapter(adapterNiveau);

    }
}