package com.example.lsddevinet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.lsddevinet.R;

public class QuitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quitter);
    }
    public void onClickQuitter(View view) {
        System.exit(0);
    }

    public void onClickAnnuler(View view) {
        finish();
    }
}