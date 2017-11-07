package com.example.skouchi.citytrax;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VolunteerCreationPage extends AppCompatActivity {

    private EditText name, orgName, orgAddress, date, hoursWorked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_creation_page);

        name = (EditText) findViewById(R.id.name);
        orgName = (EditText) findViewById(R.id.orgName);
        orgAddress = (EditText) findViewById(R.id.orgEmail);
        date = (EditText) findViewById(R.id.date);
        hoursWorked = (EditText) findViewById(R.id.hoursWorked);

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
