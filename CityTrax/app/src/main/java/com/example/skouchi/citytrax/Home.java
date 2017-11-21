package com.example.skouchi.citytrax;


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

    private ArrayList<VolunteeredDetail> volunteeredList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView emptyListText = (TextView) findViewById(R.id.empty_list_text);
        recyclerView = (RecyclerView) findViewById(R.id.volunteeredListRV);
        JSONData jsonData = new JSONData();
        volunteeredList = jsonData.getJSONData(getApplicationContext());

        if (volunteeredList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyListText.setVisibility(View.VISIBLE);
        } else {
            emptyListText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        FloatingActionButton addFab = (FloatingActionButton) findViewById(R.id.add_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VolunteerCreationPage.class));
            }
        });
    }

    // Setup recycler view to display json data
    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        VolunteeredListRV volunteeredListRV = new VolunteeredListRV(getApplicationContext(), volunteeredList);
        recyclerView.setAdapter(volunteeredListRV);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecyclerView();
    }
}
