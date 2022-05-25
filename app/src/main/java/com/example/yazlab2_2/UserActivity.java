package com.example.yazlab2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    Button btnrota;
    Button btncikis;
    Spinner dropdown;
    String selected;
    String all;
    DBHelper2 DB2;
    ArrayList <String> durations = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        dropdown = findViewById(R.id.spinner1);


        String[] items = new String[]{"seciniz...","Basiskele", "Cayirova", "Darica","Derince","Dilovasi","Gebze",
        "Golcuk","Kandira","Karamursel","Kartepe","Korfez","Izmit"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        DB2 = new DBHelper2(this);


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = (String) dropdown.getItemAtPosition(position); //
                // String value= selItem.getTheValue(); //getter method
                if(!DB2.CheckIsInDB(selected)) {   // veri tabanında yoksa kaydet
                    DB2.insertData(selected);
                    DB2.getCount(selected,1);
                }
                 DB2.getRowsAsArrayList("durations");
               // System.out.println(all);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });




        btnrota = (Button) findViewById(R.id. btnkullanicirota);
        btnrota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);


                }
        });

        btncikis = (Button) findViewById(R.id. btnkullanicicikis);
        btncikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
}
}