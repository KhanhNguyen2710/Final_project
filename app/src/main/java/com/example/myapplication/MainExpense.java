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
    //int trip_id;

    ArrayList<String> expense_id;
    ArrayList<String> type;
    ArrayList<String> amount;
    ArrayList<String> time;
    ArrayList<String> expense_trip_id;
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
                String trip_id = getIntent().getStringExtra("trip_ID");
                intent.putExtra("trip_id", trip_id);
                startActivity(intent);
            }
        });
        myDB = new Database(MainExpense.this);
        expense_trip_id = new ArrayList<>();
        expense_id = new ArrayList<>();
        type = new ArrayList<>();
        amount = new ArrayList<>();
        time = new ArrayList<>();


        storeDataExpense();


        expenseAdapter = new ExpenseAdapter(MainExpense.this, this,expense_id,
                type, amount, time);
        recyclerViewExpense.setAdapter(expenseAdapter);
        recyclerViewExpense.setLayoutManager(new LinearLayoutManager(MainExpense.this));
    }

    private void storeDataExpense() {

        // dòng này làm mất dữ liệu hiện ra màng hình những vẫn ghi vào database
        // add vào database nhưng ko hiện ( or 1 page mới )
        String trip_id = getIntent().getStringExtra("trip_ID"); //
        Cursor cursor = myDB.readAllDataExpense(trip_id);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                expense_id.add(cursor.getString(0));
                type.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                time.add(cursor.getString(3));
                expense_trip_id.add(cursor.getString(4));
            }
        }
    }
}