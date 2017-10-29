package com.example.skouchi.citytrax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private Button saveButton;
    private static final String TAG = "SAVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        saveButton = (Button) findViewById(R.id.buttonStart);
        saveButton.setOnClickListener(Register.this);
    }

    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent(this, Details.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.d(TAG, "Save Button Tapped!");
        }
    }
}
