package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class AddExpense extends AppCompatActivity {

    EditText Amount_input;
    Button Add_button;
    TextView Time;
    Spinner Type_status;
    String Type;
    int expense_trip_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        Amount_input = findViewById(R.id.Amount_input);
        Time = findViewById(R.id.Time);

        // Spinner
        Type_status = (Spinner) findViewById(R.id.Type_status);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, workStatusArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Type_status.setAdapter((dataAdapter));

       /* Type_status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Type = adapterView.getItemAtPosition(position).toString().trim();
                Toast.makeText(getApplicationContext(), "Type: "+ Type,Toast.LENGTH_SHORT).show();
            }
        });
        Bundle extras = getIntent().getExtras();
        expense_trip_id = extras.getInt("id");
        Add_button = findViewById(R.id.Add_button);
        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(AddExpense.this);
                // thứ tự

                myDB.addDataExpense(expense_trip_id,Type,
                        Amount_input.getText().toString().trim(),
                        Time.getText().toString().trim());

            }
        });*/
    }

    private final String[] workStatusArray = {
            "Food",
            "Travel",
            "Transport"
    };
    public void showDatePickerDialog3(View v) {
        DialogFragment newFragment = new DateOfExpense();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB3(LocalDate dob) {
        TextView dobText = (TextView) findViewById(R.id.Time);
        dobText.setText(dob.toString());
    }
}