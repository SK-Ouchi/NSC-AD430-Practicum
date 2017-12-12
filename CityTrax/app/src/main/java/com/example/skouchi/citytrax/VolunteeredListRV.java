package com.example.skouchi.citytrax;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class VolunteeredListRV extends RecyclerView.Adapter<VolunteeredListRV.ViewHolder>{

    private ArrayList<VolunteeredDetail> volunteeredList;
    private Context context;
    private static final String TAG = " VolunteeredListRV";


    public VolunteeredListRV(Context context, ArrayList<VolunteeredDetail> volunteeredList){

       this.volunteeredList = volunteeredList;

        this.context = context;
    }

    @Override
    public VolunteeredListRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(context).inflate(R.layout.volunteered_list_row, parent,false);

        return new ViewHolder(view1);

    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, final int position) {
        final String mName = "onBindViewHolder | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        Log.d(TAG, mName + "position: " + position);

        Vholder.date.setText(volunteeredList.get(position).getDate());

        Vholder.orgName.setText(volunteeredList.get(position).getOrgName());

        Vholder.hoursWorked.setText(volunteeredList.get(position).getHoursWorked());

        Logs.logEnd(TAG, mName);
    }


        @Override
    public int getItemCount(){

        String mName = "getItemCount | "; // Method Name for Logging
        Logs.logStart(TAG, mName);
        Log.d(TAG, mName + "Size: " + volunteeredList.size());
        Logs.logEnd(TAG, mName);

        return volunteeredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView date, orgName, hoursWorked;

        public ViewHolder(View v){
            super(v);
            date = (TextView) v.findViewById(R.id.date_row);
            orgName = (TextView) v.findViewById(R.id.org_name_row);
            hoursWorked = (TextView) v.findViewById(R.id.hours_row);
        }
    }
}