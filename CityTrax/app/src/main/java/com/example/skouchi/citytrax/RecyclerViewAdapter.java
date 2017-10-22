package com.example.skouchi.citytrax;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Skouchi on 10/20/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] values;
    Context context1;

    public RecyclerViewAdapter(Context context2,String[] values2){

        values = values2;

        context1 = context2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View v){

            super(v);

            textView = (TextView) v.findViewById(R.id.textview1);

        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(context1).inflate(R.layout.recyclerview_items,parent,false);

        ViewHolder viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    //Change Colors here!
    public void onBindViewHolder(ViewHolder Vholder, int position){

        Vholder.textView.setText(values[position]);

        Vholder.textView.setBackgroundColor(Color.CYAN);

        Vholder.textView.setTextColor(Color.BLUE);

    }

    @Override
    public int getItemCount(){

        return values.length;
    }
}