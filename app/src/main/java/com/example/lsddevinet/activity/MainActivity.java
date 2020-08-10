package com.example.lsddevinet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        MotViewModel motVM = ViewModelProviders.of(this).get(MotViewModel.class);
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

//    /**
//     * Définition des actions
//     * @param item
//     * @return
//     */
//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//        switch (item.getItemId()){
//            case R.id.action_Accueil:
//                Intent intentMain = new Intent(this, MainActivity.class);
//                startActivity(intentMain);
//                return true;
//            case R.id.action_settings:
//                Intent intentParametre = new Intent(this, .class);
//                startActivity(intentParametre);
//                return true;
//            case R.id.action_APropos:
//                Intent intentAPropos = new Intent(this, .class);
//                startActivity(intentAPropos);
//                return true;
//            case R.id.action_PagePrecedente:
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }


}