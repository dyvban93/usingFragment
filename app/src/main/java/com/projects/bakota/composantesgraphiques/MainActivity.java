package com.projects.bakota.composantesgraphiques;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.test_spinner);
        listView = (ListView) findViewById(R.id.liste);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                this.getResources().getStringArray(R.array.pref_langage_options)
                                                );
        spinner.setAdapter(adapter);

        String[] array = {"Papa","Tata","Olomo","Abena"};

       final ArrayList<String>  arrayList = new ArrayList<String>(Arrays.asList(array));

        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.item,R.id.item_text,arrayList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,arrayList.get(i) +" " + spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

    }
}
