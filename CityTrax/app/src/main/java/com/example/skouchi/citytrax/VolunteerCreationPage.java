package com.example.skouchi.citytrax;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class VolunteerCreationPage extends AppCompatActivity {

    private EditText name, orgName, orgAddress, date, hoursWorked;
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_creation_page);

        name = (EditText) findViewById(R.id.name);
        orgName = (EditText) findViewById(R.id.orgName);
        orgAddress = (EditText) findViewById(R.id.orgEmail);
        hoursWorked = (EditText) findViewById(R.id.hoursWorked);

        //Add calendar settings for date picker
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog
                        (VolunteerCreationPage.this,
                                android.R.style.Theme_Holo_Light_Dialog,
                                onDateSetListener,
                                year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month +1;
                String dateDisplay = month + "/" + day + "/" + year;
                date.setText(dateDisplay);

            }
        };

        //FAB Save
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = getDataInJSON();
                saveDataToFile(jsonObject);
                startActivity(new Intent(getApplicationContext(), Home.class));
                Snackbar.make(view, "Information Saved Successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //FAB Home
        FloatingActionButton fabHome = (FloatingActionButton) findViewById(R.id.fabHome);
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });
    }

    // Return data as JSON Object
    private JSONObject getDataInJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name.getText().toString());
            jsonObject.put("orgName", orgName.getText().toString());
            jsonObject.put("orgAddress", orgAddress.getText().toString());
            jsonObject.put("date", date.getText().toString());
            jsonObject.put("hoursWorked", hoursWorked.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // Save JSON data to local file
    private void saveDataToFile(JSONObject jsonObject) {
        JSONData jsonData = new JSONData();
        JSONArray jsonArray = new JSONArray();
        if (jsonData.getJSON(getApplicationContext()) != null) {
            try {
                jsonArray = new JSONArray(jsonData.getJSON(getApplicationContext()));
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            jsonArray.put(jsonObject);
        }
        JSONData appendedJSONData = new JSONData();
        appendedJSONData.saveJSON(getApplicationContext(), jsonArray.toString());
    }

}
