package com.example.skouchi.citytrax;

import android.util.Log;

/**
 * Created by brysongood on 11/29/2017.
 */

public class Logs {
    private static final String TAG = " Logs";

    public static String validateString(String str) {
        String mName = "validateString | "; // Method Name for Logging
        //Log.d(TAG, mName + "String : " + str);
        if(str != null && !str.isEmpty()) {
            //Log.d(TAG, mName + "Return : " + str);
            return str;
        }
        //Log.d(TAG, mName + "Return : Null");
        return "Null";
    }
}
