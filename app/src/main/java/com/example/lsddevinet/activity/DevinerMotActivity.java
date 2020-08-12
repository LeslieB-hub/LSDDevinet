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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.CategorieViewModel;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DevinerMotActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12 = null;
    List<Mot> motsTempo = new ArrayList<>();
    int id, nbRandom;
    Random random = new Random();
    Mot motTire = null;
    String mot = null;
    ArrayList<Button> buttons = new ArrayList<Button>();
    EditText lettreTape1, lettreTape2, lettreTape3, lettreTape4, lettreTape5, lettreTape6, lettreTape7,
            lettreTape8, lettreTape9,lettreTape10, lettreTape11, lettreTape12 = null;
    ArrayList<EditText> editTexts = new ArrayList<EditText>();
    String lettre= new String();
    String motPropose = new String();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviner_mot);



        //Récupérer les boutons
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
        //mettre les boutons dans une liste
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(btn4);
        buttons.add(btn5);
        buttons.add(btn6);
        buttons.add(btn7);
        buttons.add(btn8);
        buttons.add(btn9);
        buttons.add(btn10);
        buttons.add(btn11);
        buttons.add(btn12);

        //Récupérer les editText
        lettreTape1 = findViewById(R.id.et_lettre1);
        lettreTape2 = findViewById(R.id.et_lettre2);
        lettreTape3 = findViewById(R.id.et_lettre3);
        lettreTape4 = findViewById(R.id.et_lettre4);
        lettreTape5 = findViewById(R.id.et_lettre5);
        lettreTape6 = findViewById(R.id.et_lettre6);
        lettreTape7 = findViewById(R.id.et_lettre7);
        lettreTape8 = findViewById(R.id.et_lettre8);
        lettreTape9 = findViewById(R.id.et_lettre9);
        lettreTape10 = findViewById(R.id.et_lettre10);
        lettreTape11 = findViewById(R.id.et_lettre11);
        lettreTape12 = findViewById(R.id.et_lettre12);
        //mettre les editText dans une liste
        editTexts.add(lettreTape1);
        editTexts.add(lettreTape2);
        editTexts.add(lettreTape3);
        editTexts.add(lettreTape4);
        editTexts.add(lettreTape5);
        editTexts.add(lettreTape6);
        editTexts.add(lettreTape7);
        editTexts.add(lettreTape8);
        editTexts.add(lettreTape9);
        editTexts.add(lettreTape10);
        editTexts.add(lettreTape11);
        editTexts.add(lettreTape12);

        Intent intent = getIntent();
        Categorie categorie = intent.getParcelableExtra("Categorie");
        id = categorie.getId();

        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);

        LiveData<List<Mot>> observateur = motVM.getMotByCategorie(id);

        observateur.observe(this, new Observer<List<Mot>>() {
            @Override
            public void onChanged(List<Mot> mots ) {
//                for (Mot mot:mots) {
//                    motsTempo.add(mot);
//                }

                //Tirer un mot au hasard
                nbRandom = random.nextInt(mots.size()-1);
                motTire = mots.get(nbRandom);

                //Récupérer le mot
                mot = motTire.getMot();

                //mettre les lettres ds un tableau
                char[] motTireAMelanger = mot.toCharArray();

                //mélanger les lettres du tableau
                int posAlea;
                for (int i=0; i < mot.length(); i++){
                    posAlea = random.nextInt(mot.length());
                    char temp = motTireAMelanger[i];
                    motTireAMelanger[i] = motTireAMelanger[posAlea];
                    motTireAMelanger[posAlea] = temp;
                }

                //mettre une lettre dans un bouton
                for(int i=0; i < motTireAMelanger.length; i++){
                    buttons.get(i).setText(String.valueOf(motTireAMelanger[i]));
                }

                //Afficher le nombre de bouton
                switch (id){
                    case 5:
                        btn5.setVisibility(View.VISIBLE);
                        lettreTape5.setVisibility(View.VISIBLE);

                        break;
                    case 6:
                        for(int i=4; i < 6; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 7:
                        for(int i=4; i < 7; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 8:
                        for(int i=4; i < 8; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 9:
                        for(int i=4; i < 9; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 10:
                        for(int i=4; i < 10; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 11:
                        for(int i=4; i < 11; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                    case 12:
                        for(int i=4; i < 12; i++){
                            buttons.get(i).setVisibility(View.VISIBLE);
                            editTexts.get(i).setVisibility(View.VISIBLE);
                        }
                        break;
                }

                Uri imgUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + mot);

                ((ImageView) DevinerMotActivity.this.findViewById(R.id.image)).setImageURI(imgUri);
                Log.i("Devinet", "Nombre aléatoire : " + nbRandom + " taille de la liste : "+mots.size());
                Toast.makeText(DevinerMotActivity.this, "Test" + motTire + nbRandom, Toast.LENGTH_LONG).show();

            }
        });

//        Mot mottirer = motsTempo.get(1);
//        Toast.makeText(DevinerMotActivity.this, "Test" + mottirer, Toast.LENGTH_LONG).show();
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

    public void onClickBtn1(View view) {
        lettre = btn1.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        //désactiver le bouton après le click
        btn1.setEnabled(false);
    }

    public void onClickBtn8(View view) {
        lettre = btn8.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn8.setEnabled(false);
    }

    public void onClickBtn3(View view) {
        lettre = btn3.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn3.setEnabled(false);
    }


    public void onClickBtn10(View view) {
        lettre = btn10.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn10.setEnabled(false);
    }


    public void onClickBtn5(View view) {
        lettre = btn5.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn5.setEnabled(false);
    }


    public void onClickBtn12(View view) {
        lettre = btn12.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn12.setEnabled(false);
    }

    public void onClickBtn7(View view) {
        lettre = btn7.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn7.setEnabled(false);
    }

    public void onClickBtn2(View view) {
        lettre = btn2.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
            break;
            }
        }
        btn2.setEnabled(false);
    }

    public void onClickBtn9(View view) {
        lettre = btn9.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn9.setEnabled(false);
    }

    public void onClickBtn4(View view) {
        lettre = btn4.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn4.setEnabled(false);
    }

    public void onClickBtn11(View view) {
        lettre = btn11.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn11.setEnabled(false);
    }

    public void onClickBtn6(View view) {
        lettre = btn6.getText().toString();
        Toast.makeText(DevinerMotActivity.this, "lettre tapée " + lettre, Toast.LENGTH_LONG).show();
        Log.i("Shop", "lettre tapée " + lettre);
        //mettre la lettre dans le editText et dans mot proposé
        for(int i=0; i < 12; i++){
            if (editTexts.get(i).getText().toString().isEmpty()){
                editTexts.get(i).setText(lettre);
                break;
            }
        }
        btn6.setEnabled(false);
    }

    public void onClickClearEditTexts(View view) {
        for(int i=0; i < 12; i++){
                editTexts.get(i).setText("");
                buttons.get(i).setEnabled(true);
            }
        }


    public void onClickValider(View view) {
        //mettre les lettres dans le mot propose

        for(int i=0; i < 12; i++){
            motPropose += editTexts.get(i).getText();
        }
        motPropose = motPropose.trim();
        Log.i("Devinet", "mot tapée " + motPropose);
        //Verifier le mot proposé avec le mot tiré
        motTire.setProposition(motPropose);
        if (mot.equals(motPropose)){
            Toast.makeText(DevinerMotActivity.this, "Gagné " + motPropose + mot, Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(DevinerMotActivity.this, "PERDU !! Le mot était "+ mot, Toast.LENGTH_LONG).show();
        }

        Log.i("Devinet", "mot tiré " + motTire.toString());
    }




    public void onClickSuivant(View view) {
    }
}