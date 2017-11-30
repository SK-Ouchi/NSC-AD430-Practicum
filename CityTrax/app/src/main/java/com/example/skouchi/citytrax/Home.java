package com.example.skouchi.citytrax;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private static final String TAG = " Home";
    private ArrayList<VolunteeredDetail> volunteeredList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String mName = "onCreate | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.add_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "addFab Clicked | startActivity : VolunteerCreationPage ");
                startActivity(new Intent(getApplicationContext(), VolunteerCreationPage.class));
            }
        });
        Logs.logEnd(TAG, mName);
    }

    @Override
    protected void onResume() {
        String mName = "onResume | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        super.onResume();

        TextView emptyListText = (TextView) findViewById(R.id.empty_list_text);
        recyclerView = (RecyclerView) findViewById(R.id.volunteeredListRV);

        int resultVL = checkForVolunteeredList(getApplicationContext());

        if (resultVL != 0 && resultVL != 1) {
            Log.d(TAG, mName + "Error : value not valid | resultVL : " + resultVL);
        } else {
            if (resultVL == 0) {
                Log.d(TAG, mName + "Recycler View : Not Visible");
                recyclerView.setVisibility(View.GONE);
                emptyListText.setVisibility(View.VISIBLE);
            }
            if (resultVL == 1) {
                Log.d(TAG, mName + "Recycler View : Visible");
                emptyListText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                setupRecyclerView();
            }
        }

        Logs.logEnd(TAG, mName);
    }
    // Setup recycler view to display json data
    private void setupRecyclerView() {
        String mName = "setupRecyclerView | "; // Method Name for Logging
        Logs.logStart(TAG, mName);

        // Update volunteeredList
        JSONData jsonData = new JSONData();
        volunteeredList = jsonData.getJSONData(getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        VolunteeredListRV volunteeredListRV = new VolunteeredListRV(getApplicationContext(), volunteeredList);
        recyclerView.setAdapter(volunteeredListRV);
        Logs.logEnd(TAG, mName);
    }
    /*  Return 1 if VolunteeredList exists || 0 if VolunteeredList does not exists */
    private int checkForVolunteeredList(Context context) {
        String mName = "checkForVolunteeredList | "; // Method Name for Logging
        Logs.logStart(TAG, mName);

        JSONData jsonData = new JSONData();
        volunteeredList = jsonData.getJSONData(context);

        if (!(volunteeredList.isEmpty())) {
            Log.d(TAG, mName + "volunteeredList : NOT EMPTY");
            Logs.logEnd(TAG, mName);
            return 1; // True
        } else {
            Log.d(TAG, mName + "volunteeredList : EMPTY");
            Logs.logEnd(TAG, mName);
            return 0; // False
        }
    }
}
