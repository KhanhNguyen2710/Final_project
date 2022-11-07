package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList trip_id, name, destination, date, risks, description;

   // int position;


    Adapter(Activity activity, Context context, ArrayList trip_id, ArrayList name, ArrayList destination, ArrayList date,
            ArrayList risks, ArrayList description) {
        this.activity = activity;
        this.context = context;
        this.trip_id = trip_id;
        this.name = name;
        this.destination = destination;
        this.date = date;
        this.risks = risks;
        this.description = description;

    }


    @NonNull
    @Override //Adapter.MyViewHolder
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
      //  this.position = position;

//      holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.trip_id_txt.setText(String.valueOf(position + 1));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.destination_txt.setText(String.valueOf(destination.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.risks_txt.setText(String.valueOf(risks.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Activity.class);
                intent.putExtra("trip_id", String.valueOf(trip_id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("destination", String.valueOf(destination.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("risks", String.valueOf(risks.get(position)));
                intent.putExtra("description", String.valueOf(description.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt, name_txt, destination_txt, date_txt, risks_txt, description_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            destination_txt = itemView.findViewById(R.id.destination_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            risks_txt = itemView.findViewById(R.id.risks_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
