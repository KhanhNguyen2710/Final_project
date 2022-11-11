package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {

    private Context context;
    Activity activity ;
    private ArrayList expense_id, type, amount, time;

    ExpenseAdapter(Activity activity, Context context, ArrayList expense_id, ArrayList type, ArrayList amount,
                   ArrayList time) {
        this.activity=activity;
        this.context = context;
        this.expense_id = expense_id;
        this.type = type;
        this.amount = amount;
        this.time = time;

    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.expense_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.AmountExpense_txt.setText(String.valueOf(amount.get(position)));
        holder.TypeExpense_txt.setText(String.valueOf(type.get(position)));
        holder.TimeExpense_txt.setText(String.valueOf(time.get(position)));
        /*holder.mainLayoutExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateTrip.class);
                intent.putExtra("expense_id", String.valueOf(expense_id.get(position)));
                intent.putExtra("amount", String.valueOf(amount.get(position)));
                intent.putExtra("type", String.valueOf(type.get(position)));
                intent.putExtra("time", String.valueOf(time.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView AmountExpense_txt, TypeExpense_txt, TimeExpense_txt;
        LinearLayout mainLayoutExpense;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            AmountExpense_txt = itemView.findViewById(R.id.AmountExpense_txt);
            TypeExpense_txt = itemView.findViewById(R.id.TypeExpense_txt);
            TimeExpense_txt = itemView.findViewById(R.id.TimeExpense_txt);

        }
    }
}
