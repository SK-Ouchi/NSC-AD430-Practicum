package com.example.skouchi.citytrax;

import android.util.Log;

public class VolunteeredDetail {

    private static final String TAG = " VolunteeredDetail";
    private String name, orgName, orgAddress, date, hoursWorked;

    public VolunteeredDetail(String name, String orgName, String orgAddress, String date, String hoursWorked) {
        String mName = "Constructor | "; // Method Name for Logging
        Log.d(TAG,mName + "---------- START ----------");
        /* Start - Logging for VolunteeredDetail Constructor */
        Log.d(TAG,mName + "       name : " + name);
        Log.d(TAG,mName + "    orgName : " + orgName);
        Log.d(TAG,mName + " orgAddress : " + orgAddress);
        Log.d(TAG,mName + "       date : " + date);
        Log.d(TAG,mName + "hoursWorked : " + hoursWorked);
        Log.d(TAG,mName + "----------- End -----------");
        /* End  - Logging for VolunteeredDetail Constructor */
        this.name = name;
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

    public String getName() {
        Log.d(TAG,"       getName() | Return : " + Logs.validateString(name));
        return name;
    }

    public String getOrgName() {
        Log.d(TAG,"    getOrgName() | Return : " + Logs.validateString(orgName));
        return orgName;
    }

    public String getOrgAddress() {
        Log.d(TAG," getOrgAddress() | Return : " + Logs.validateString(orgAddress));
        return orgAddress;
    }

    public String getDate() {
        Log.d(TAG,"       getDate() | Return : " + Logs.validateString(date));
        return date;
    }

    public String getHoursWorked() {
        Log.d(TAG,"getHoursWorked() | Return : " + Logs.validateString(hoursWorked));
        return hoursWorked;
    }
}