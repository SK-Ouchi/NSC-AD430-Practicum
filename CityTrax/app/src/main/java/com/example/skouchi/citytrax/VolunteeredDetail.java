package com.example.skouchi.citytrax;

public class VolunteeredDetail {

    private String name, orgName, orgAddress, date, hoursWorked;

    public VolunteeredDetail(String name, String orgName, String orgAddress, String date, String hoursWorked) {
        this.name = name;
        this.orgName = orgName;
        this.orgAddress = orgAddress;
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

    public String getName() {
        return name;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public String getDate() {
        return date;
    }

    public String getHoursWorked() {
        return hoursWorked;
    }
}
