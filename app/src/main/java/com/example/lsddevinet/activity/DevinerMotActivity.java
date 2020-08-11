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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

import java.util.List;

public class DevinerMotActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviner_mot);

        Intent intent = getIntent();
        Categorie categorie = intent.getParcelableExtra("Categorie");
        int id = categorie.getId();

        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);

        LiveData<List<Mot>> observateur = motVM.getMotByCategorie(id);

        observateur.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots ) {
                Mot premierMot = mots.get(0);
                String mot = premierMot.getMot();

                Uri imgUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + mot);

                ((ImageView) DevinerMotActivity.this.findViewById(R.id.image)).setImageURI(imgUri);
                ((TextView) DevinerMotActivity.this.findViewById(R.id.mot_a_deviner)).setText(mot);

                Toast.makeText(DevinerMotActivity.this, "Test" + premierMot, Toast.LENGTH_LONG).show();
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
}