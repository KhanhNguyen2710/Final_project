package com.example.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private ArrayList trip_id, name, destination, date, risks, description;
// fix
    Adapter(Context context, ArrayList trip_id, ArrayList name, ArrayList destination, ArrayList date,
            ArrayList risks, ArrayList description) {
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
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        holder.trip_id_txt.setText(String.valueOf(trip_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.destination_txt.setText(String.valueOf(destination.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.risks_txt.setText(String.valueOf(risks.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
    }

    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt, name_txt, destination_txt, date_txt, risks_txt, description_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            destination_txt = itemView.findViewById(R.id.destination_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            risks_txt = itemView.findViewById(R.id.risks_txt);
            description_txt = itemView.findViewById(R.id.description_txt);
        }
    }
}
