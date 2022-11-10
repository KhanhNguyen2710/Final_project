package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainExpense extends AppCompatActivity {

    RecyclerView recyclerViewExpense;
    FloatingActionButton add_button_expense;

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
                startActivity(intent);
            }
        });
    }


}