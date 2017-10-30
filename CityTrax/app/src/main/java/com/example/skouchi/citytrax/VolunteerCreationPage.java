package com.example.skouchi.citytrax;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import java.io.File;

public class VolunteerCreationPage extends AppCompatActivity {

    private EditText name, orgName, orgAddress, date, hoursWorked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_creation_page);

        name = (EditText) findViewById(R.id.name);
        orgName = (EditText) findViewById(R.id.orgName);
        orgAddress = (EditText) findViewById(R.id.orgAddress);
        date = (EditText) findViewById(R.id.date);
        hoursWorked = (EditText) findViewById(R.id.hoursWorked);


        //FAB Save
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information Saved Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(), Home.class));

            }
        });

        //FAB Home
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fabHome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });
    }

}
