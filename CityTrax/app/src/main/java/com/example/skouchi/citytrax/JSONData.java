package com.example.skouchi.citytrax;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONData {

    private static final String TAG = " JSONData";
    private String fileName = "data.json";

    public JSONData() {

    }

    // Retrieve data from a json file in arraylist format
    public ArrayList<VolunteeredDetail> getJSONData(Context context) {
        String mName = "getJSONData | "; // Method Name for Logging
        Logs.logStart(TAG, mName);

        ArrayList<VolunteeredDetail> volunteeredList = new ArrayList<>();
        String jsonString = this.getJSON(context);
        if (jsonString != null ) {
            Log.d(TAG,mName + "jsonString : Not Null");
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
        /* Start - getJSONData Logging */
        String contents; // YES || NO , used in Logging
        Log.d(TAG, mName + "volunteeredList - " + volunteeredList);
        if (!(volunteeredList.isEmpty())) { // If list is not empty : true
            Log.d(TAG, mName + "volunteeredList : NOT EMPTY");
            contents = "YES";
        } else {
            Log.d(TAG, mName + "volunteeredList : EMPTY");
            contents = "NO";

        }
        Log.d(TAG,mName + "Returning ArrayList : " + contents);
        Logs.logEnd(TAG, mName);
        return volunteeredList;
    }

    // Save JSON data to local file
    public void saveDataToFile(JSONObject jsonObject, Context context) {
        String mName = "saveDataToFile | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        JSONArray jsonArray = new JSONArray();
        String jsonString = this.getJSON(context);
        if (jsonString != null) {
            Log.d(TAG,mName + "jsonString : Not Null");
            try {
                jsonArray = new JSONArray(jsonString);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG,mName + "jsonString : Null");
            jsonArray.put(jsonObject);
        }
        this.saveJSON(context, jsonArray.toString());
        Logs.logEnd(TAG, mName);
    }

    // Save JSON data to local file
    public void saveJSON(Context context, String jsonData) {
        String mName = "saveJSON | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        try {
            String filePath = context.getFilesDir().getPath() + "/" + fileName;
            Log.d(TAG,mName + "filepath/name : " + filePath);
            FileWriter file = new FileWriter(filePath, false);
            file.write(jsonData);
            file.flush();
            file.close();
            Log.d(TAG,mName + "| No Error | File " + fileName + " Saved |");
        } catch (IOException e) {
            Log.d(TAG,mName + "| Error");
        }
        Logs.logEnd(TAG, mName);
    }

    // Retrieve data from a local json file
    public String getJSON(Context context) {
        String mName = "getJSON | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            Log.d(TAG,mName + "filepath/name : " + f);
            //check if file exists
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            if (size > 0) {
                Log.d(TAG,mName + "File " + fileName + " | Has Contents : YES ");
            }
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            Logs.logEnd(TAG, mName);
            return new String(buffer);
        } catch (IOException e) {
            Log.d(TAG,mName + "Error");
            Log.d(TAG,mName + "File " + fileName + " | Has Contents : NO");
            Logs.logEnd(TAG, mName);
            return null;
        }
    }

}
