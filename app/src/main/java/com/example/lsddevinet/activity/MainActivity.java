package com.example.lsddevinet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

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
}