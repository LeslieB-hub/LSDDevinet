package com.example.lsddevinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

import java.util.ArrayList;
import java.util.List;

public class DevinerMotActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12 = null;
    List<Mot> motsTempo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviner_mot);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn10 = findViewById(R.id.btn_10);
        btn11 = findViewById(R.id.btn_11);
        btn12 = findViewById(R.id.btn_12);

        Intent intent = getIntent();
        Categorie categorie = intent.getParcelableExtra("Categorie");
        int id = categorie.getId();

        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);

        LiveData<List<Mot>> observateur = motVM.getMotByCategorie(id);

        observateur.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots ) {
                for (Mot mot:mots) {
                    motsTempo.add(mot);
                }

                Mot premierMot = mots.get(0);
                String mot = premierMot.getMot();

                Uri imgUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + mot);

                ((ImageView) DevinerMotActivity.this.findViewById(R.id.image)).setImageURI(imgUri);
                ((TextView) DevinerMotActivity.this.findViewById(R.id.mot_a_deviner)).setText(mot);

                Toast.makeText(DevinerMotActivity.this, "Test" + premierMot, Toast.LENGTH_LONG).show();
            }
        });

//        Mot mottirer = motsTempo.get(1);
//        Toast.makeText(DevinerMotActivity.this, "Test" + mottirer, Toast.LENGTH_LONG).show();
        btn1.setVisibility(View.VISIBLE);
        btn1.setText("m");
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}