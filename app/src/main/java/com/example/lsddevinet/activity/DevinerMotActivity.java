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


    private List<Mot> mots = null;
    Mot premierMot = null;
    String imgNom = "fraise.jpeg";
    String motNom = "fraise";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviner_mot);

        Intent intent = getIntent();
        Categorie categorie = intent.getParcelableExtra("Categorie");
        int id = categorie.getId();

        ImageView img = findViewById(R.id.image);
        TextView motADeviner = findViewById(R.id.mot_a_deviner);

        TextView idCat = findViewById(R.id.id_cat);
        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);

        idCat.setText(String.valueOf(id));



        LiveData<List<Mot>> observateur = motVM.getMotByCategorie(id);

        observateur.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots) {
                premierMot = mots.get(0);
                imgNom = premierMot.getImg();
                motNom = premierMot.getMot();

                Toast.makeText(DevinerMotActivity.this, "Test" + premierMot, Toast.LENGTH_LONG).show();
            }
        });

        Uri imgUri=Uri.parse("res/drawable/" + imgNom);
        img.setImageURI(imgUri);
        motADeviner.setText(motNom);



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