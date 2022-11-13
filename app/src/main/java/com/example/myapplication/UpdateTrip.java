package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class UpdateTrip extends AppCompatActivity {

    EditText Destination_input, Name_input, Description_input;
    Button Save_button, Delete_button, Expense_button;
    RadioGroup Risks_input;
    RadioButton radioChecked;
    TextView Date_input;
    String trip_id, name, destination, date, risks, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip);

        Name_input = findViewById(R.id.Name_input2);
        Destination_input = findViewById(R.id.Destination_input2);
        Date_input = findViewById(R.id.Date_input2);
        Risks_input = findViewById(R.id.Risks_input2);
        Description_input = findViewById(R.id.Description_input2);
        /*int checkedID2 = Risks_input.getCheckedRadioButtonId();
        radioChecked = findViewById(checkedID2);*/
        // String radioText = radioChecked.getText().toString();
        Delete_button = findViewById(R.id.Delete_button);

        Save_button=findViewById(R.id.Update_button2);

        getAndSetIntentData();

        // set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }
        Save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedID2 = Risks_input.getCheckedRadioButtonId();
                radioChecked = findViewById(checkedID2);
                String radioText = radioChecked.getText().toString();

                Database myDB = new Database(UpdateTrip.this);
                name = Name_input.getText().toString().trim();
                destination = Destination_input.getText().toString().trim();
                date = Date_input.getText().toString().trim();
                risks = radioText;
                description = Description_input.getText().toString().trim();
                myDB.updateDataTrip(trip_id, name, destination, date, risks, description);
            //risks = radioText;



            }
        });
        Delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


        // to ExpanseMain
        Expense_button = findViewById(R.id.Expense_button);
        Expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(UpdateTrip.this, MainExpense.class);
                    intent.putExtra("trip_ID",trip_id);
                    startActivity(intent);

            }
        });

    }
    void getAndSetIntentData() {
        if (getIntent().hasExtra("trip_id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("destination") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("risks") && getIntent().hasExtra("description")) {
            //getting data from intent
            trip_id = getIntent().getStringExtra("trip_id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            risks = getIntent().getStringExtra("risks");
            /*if(risks.equals("Yes")){
                RadioButton yes = findViewById(R.id.checkYes);
                yes.setChecked(true);
            }else{
                RadioButton no = findViewById(R.id.checkNo);
                no.setChecked(true);
            }*/
            if(risks.equals("Yes")){
                RadioButton checkYes = findViewById(R.id.checkYes);
                checkYes.setChecked(true);
            }else{
                RadioButton checkNo = findViewById(R.id.checkNo);
                checkNo.setChecked(true);
            }
            description = getIntent().getStringExtra("description");

            // Setting intent data
            Name_input.setText(name);
            Destination_input.setText(destination);
            Date_input.setText(date);
            //Risks_input.setText(ricks);
            // lỗi radio
            //radioChecked.setText(risks);

            Description_input.setText(description);
        }else{
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?" );
        builder.setMessage("Are you sure you want to delete" + name + "?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database myDB = new Database(UpdateTrip.this);
                myDB.deleteDataTrip(trip_id);
                myDB.expenseIdDelete(trip_id);
                finish();
                Intent intent = new Intent( UpdateTrip.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void showDatePickerDialog2(View v){
        DialogFragment newFragment = new DateOfUpdate();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB2(LocalDate dob){
        TextView dobText = (TextView) findViewById(R.id.Date_input2);
        dobText.setText(dob.toString());
    }
}