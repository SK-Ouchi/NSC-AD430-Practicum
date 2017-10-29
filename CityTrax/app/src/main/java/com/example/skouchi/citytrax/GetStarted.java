package com.example.skouchi.citytrax;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity implements View.OnClickListener {

    private Button startButton;
    private static final String TAG = "GetStarted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        startButton = (Button) findViewById(R.id.buttonStart);
        startButton.setOnClickListener(GetStarted.this);

    }

    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent(this, Details.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.d(TAG, "Start Button Tapped");
        }
    }
}
