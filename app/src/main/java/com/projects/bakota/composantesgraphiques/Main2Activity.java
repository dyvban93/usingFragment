package com.projects.bakota.composantesgraphiques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    String nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (getIntent() != null){

            Intent intent = getIntent();

            nom = intent.getStringExtra("nom");

            Toast.makeText(this,nom,Toast.LENGTH_LONG).show();
        }
    }
}
