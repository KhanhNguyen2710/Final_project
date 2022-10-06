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

import kotlinx.coroutines.internal.MainDispatcherFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Database myDB;
    ArrayList<String> trip_id, name, destination, date, ricks, description;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new Database(MainActivity.this);
        trip_id = new ArrayList<>();
        name = new ArrayList<>();
        destination = new ArrayList<>();
        date = new ArrayList<>();
        ricks = new ArrayList<>();
        description = new ArrayList<>();

        storeData();
        adapter = new Adapter(MainActivity.this, trip_id, name, destination,
                date, ricks, description);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    void storeData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                trip_id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                destination.add(cursor.getString(2));
                date.add(cursor.getString(3));
                ricks.add(cursor.getString(4));
                description.add(cursor.getString(5));
            }
        }
    }
}