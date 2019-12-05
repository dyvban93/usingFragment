package com.projects.bakota.composantesgraphiques;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener, SecondFormFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        if(findViewById(R.id.container) != null){

            FirstFragment firstFragment = new FirstFragment();

            getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.container, firstFragment)
                                        .addToBackStack(null)
                                        .commit();
        }
    }

    public void onFragmentInteraction(Uri uri){

    }
}
