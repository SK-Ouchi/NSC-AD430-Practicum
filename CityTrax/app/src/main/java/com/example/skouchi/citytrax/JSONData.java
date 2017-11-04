package com.example.skouchi.citytrax;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JSONData {

    private String fileName = "data.json";
    private static final String TAG = "JSONData";

    public JSONData() {
    }

    public void saveJSON(Context context, String jsonData) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName, false);
            file.write(jsonData);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e(TAG, "Writing Error: " + e.getLocalizedMessage());
        }
    }

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
            Log.e(TAG, "Reading Error: " + e.getLocalizedMessage());
            return null;
        }
    }

}
