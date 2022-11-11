package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainExpense extends AppCompatActivity {

    RecyclerView recyclerViewExpense;
    FloatingActionButton add_button_expense;
    Database myDB;
    int tripID;

    ArrayList<String> expense_id;
    ArrayList<String> expense_trip_id;
    ArrayList<String> type;
    ArrayList<String> amount;
    ArrayList<String> time;
    ArrayList<String> expense_id_trip;
    ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expense);

        recyclerViewExpense = findViewById(R.id.recyclerViewExpense);
        add_button_expense = findViewById(R.id.add_button_expense);
        add_button_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainExpense.this, AddExpense.class);
                /*tripID = Integer.parseInt(getIntent().getStringExtra("expense_trip_id"));
                intent.putExtra("expense_trip_id",tripID);*/
                startActivity(intent);
            }
        });
        myDB = new Database(MainExpense.this);
        expense_id = new ArrayList<>();
        // expense_id_trip = new ArrayList<>();

        type = new ArrayList<>();
        amount = new ArrayList<>();
        time = new ArrayList<>();

        storeDataExpense();


        expenseAdapter = new ExpenseAdapter(MainExpense.this, this, expense_id ,  type, amount, time);
        recyclerViewExpense.setAdapter(expenseAdapter);
        recyclerViewExpense.setLayoutManager(new LinearLayoutManager(MainExpense.this));
    }

    void storeDataExpense() {

       // int expense_trip_id = Integer.parseInt(getIntent().getStringExtra("expense_trip_id"));
        Cursor cursor = myDB.readAllDataExpense(tripID);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                expense_id.add(cursor.getString(0));
                type.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                time.add(cursor.getString(3));
            }
        }
    }
}