package com.example.skouchi.citytrax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class VolunteeredListRV extends RecyclerView.Adapter<VolunteeredListRV.ViewHolder>{

    private ArrayList<VolunteeredDetail> volunteeredList;
    private Context context;

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
    //Change Colors here!
    public void onBindViewHolder(ViewHolder Vholder, int position){

        Vholder.date.setText(volunteeredList.get(position).getDate());

        Vholder.orgName.setText(volunteeredList.get(position).getOrgName());

        Vholder.hoursWorked.setText(volunteeredList.get(position).getHoursWorked());

    }

    @Override
    public int getItemCount(){

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