package com.example.skouchi.citytrax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Home extends AppCompatActivity {

    private ArrayList<VolunteeredDetail> volunteeredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        volunteeredList = new ArrayList<>();
        getJSONData();
        setupRecyclerView();

        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.add_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VolunteerCreationPage.class));
            }
        });
    }

    // Retrieve json data from the local file and saves it in an arraylist
    private void getJSONData() {
        volunteeredList = new ArrayList<>();
        JSONData jsonData = new JSONData();
        JSONObject jsonObject = null;
        if (jsonData.getJSON(getApplicationContext()) != null ) {
            try {
                JSONArray jsonArray = new JSONArray(jsonData.getJSON(getApplicationContext()));
                Log.d("JSON", jsonArray.length() + " Array");
                for (int i = 0; i < jsonArray.length(); i++) {
                    String name = jsonArray.getJSONObject(i).get("name").toString();
                    String orgName = jsonArray.getJSONObject(i).get("orgName").toString();
                    String orgAddress = jsonArray.getJSONObject(i).get("orgEmail").toString();
                    String date = jsonArray.getJSONObject(i).get("date").toString();
                    String hoursWorked = jsonArray.getJSONObject(i).get("hoursWorked").toString();
                    volunteeredList.add(new VolunteeredDetail(name, orgName, orgAddress, date, hoursWorked));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Setup recycler view to display json data
    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.volunteeredListRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        VolunteeredListRV volunteeredListRV = new VolunteeredListRV(getApplicationContext(), volunteeredList);
        recyclerView.setAdapter(volunteeredListRV);
    }

}
