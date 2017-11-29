package com.example.skouchi.citytrax;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONData {

    private String fileName = "data.json";

    public JSONData() {

    }

    // Retrieve data from a json file in arraylist format
    public ArrayList<VolunteeredDetail> getJSONData(Context context) {
        ArrayList<VolunteeredDetail> volunteeredList = new ArrayList<>();
        String jsonString = this.getJSON(context);
        if (jsonString != null ) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String name = jsonArray.getJSONObject(i).get("name").toString();
                    String orgName = jsonArray.getJSONObject(i).get("orgName").toString();
                    String orgEmail = jsonArray.getJSONObject(i).get("orgEmail").toString();
                    String date = jsonArray.getJSONObject(i).get("date").toString();
                    String hoursWorked = jsonArray.getJSONObject(i).get("hoursWorked").toString();
                    volunteeredList.add(new VolunteeredDetail(name, orgName, orgEmail, date, hoursWorked));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return volunteeredList;
    }

    // Save JSON data to local file
    public void saveDataToFile(JSONObject jsonObject, Context context) {
        JSONArray jsonArray = new JSONArray();
        String jsonString = this.getJSON(context);
        if (jsonString != null) {
            try {
                jsonArray = new JSONArray(jsonString);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            jsonArray.put(jsonObject);
        }
        this.saveJSON(context, jsonArray.toString());
    }

    // Save JSON data to local file
    public void saveJSON(Context context, String jsonData) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName, false);
            file.write(jsonData);
            file.flush();
            file.close();
        } catch (IOException e) {
        }
    }

    // Retrieve data from a local json file
    public String getJSON(Context context) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            //check if file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            return null;
        }
    }

}
