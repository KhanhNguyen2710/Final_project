package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
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

        /*Type_status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Type = adapterView.getItemAtPosition(position).toString().trim();
                Toast.makeText(getApplicationContext(), "Type: "+ Type,Toast.LENGTH_SHORT).show();
            }
        });
        Bundle extras = getIntent().getExtras();
        expense_trip_id = extras.getInt("id");*/
        String trip_Id = getIntent().getStringExtra("trip_id");
        Add_button = findViewById(R.id.Add_button);
        Add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkExpense()) {
                    Database myDB = new Database(AddExpense.this);
                    // thứ tự
                    String TypeExpense = Type_status.getSelectedItem().toString();

                    // dòng này vào bị ko hiện data nhưng nó show đc dữ liệu có trong database
                    // hiện ko add vào database


                    myDB.addDataExpense(trip_Id, TypeExpense,
                            Amount_input.getText().toString().trim(),
                            Time.getText().toString().trim());

                    Intent intent = new Intent(AddExpense.this, MainExpense.class);
                    intent.putExtra("trip_ID", trip_Id);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddExpense.this, "Please enter enough information", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean checkExpense() {
                if (Amount_input.getText().toString().length() == 0) {
                    return false;
                }
                if (Time.getText().toString().length() == 0) {
                    return false;
                }
                return true;
            }

        });

    }


    private final String[] workStatusArray = {
            "Food",
            "Travel",
            "Transport",
            "Other"
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