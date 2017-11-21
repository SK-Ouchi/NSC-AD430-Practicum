package com.example.skouchi.citytrax;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class VolunteerCreationPage extends AppCompatActivity {

    private EditText name, orgName, orgEmail, date, hoursWorked;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private JSONData jsonData = new JSONData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_creation_page);

        name = (EditText) findViewById(R.id.name);
        orgName = (EditText) findViewById(R.id.orgName);
        orgEmail = (EditText) findViewById(R.id.orgEmail);
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

        String jsonString = jsonData.getJSON(getApplicationContext());
        if (jsonString != null ) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                name.setText(jsonObject.get("name").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

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
                sendEmail();
                JSONObject jsonObject = getDataInJSON();
                jsonData.saveDataToFile(jsonObject, getApplicationContext());
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
            jsonObject.put("orgEmail", orgEmail.getText().toString());
            jsonObject.put("date", date.getText().toString());
            jsonObject.put("hoursWorked", hoursWorked.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

//    Redirect user to email app with populated email fields
    protected void sendEmail() {
        String[] TO = {orgEmail.getText().toString()};
        String[] CC = {""};
        String subject = "Confirmation of Volunteer Work";
        String message = "Dear " + orgName.getText().toString() + " representative, " + name.getText().toString() + " reported working for " + hoursWorked.getText().toString() + " on " + date.getText().toString() + " at your company. Please reply to this email to confirm the volunteer work.";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(Intent.createChooser(emailIntent, "Send mail"));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
