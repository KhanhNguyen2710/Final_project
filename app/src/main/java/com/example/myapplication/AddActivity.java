package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText Destination_input, Name_input, Description_input;
    Button Save_button;
    RadioGroup Risks_input;
    RadioButton radioChecked;
    TextView Date_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Date_input = findViewById(R.id.Date_input);
        Name_input = findViewById(R.id.Name_input);
        Destination_input = findViewById(R.id.Destination_input);
        Risks_input = findViewById(R.id.Risks_input);
        Description_input = findViewById(R.id.Description_input);
        Save_button = findViewById(R.id.Save_button);
        Save_button.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Database myDB = new Database(AddActivity.this);
                int checkedID = Risks_input.getCheckedRadioButtonId();
                radioChecked = findViewById(checkedID);
                String textRadio = radioChecked.getText().toString().trim();
                // thứ tự
                myDB.addData(Name_input.getText().toString().trim(),
                        Destination_input.getText().toString().trim(),
                        Date_input.getText().toString().trim(),
                        textRadio,
                        Description_input.getText().toString().trim());

                String strName = Name_input.getText().toString();
                String strDestination = Destination_input.getText().toString();
                String strDate = Date_input.getText().toString();
                String strRisks = radioChecked.getText().toString().trim();
                String strDescription = Description_input.getText().toString();

                displayNextAlert(strName, strDestination, strDate, strRisks, strDescription);
            }

        });


    }
    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB(LocalDate dob){
        TextView dobText = (TextView) findViewById(R.id.Date_input);
        dobText.setText(dob.toString());
    }

    // show entered data

    private void displayNextAlert(String strName, String strDestination, String strDate, String strRisks, String strDescription){
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage(/*"Details entered: " +*/
                "\n" + "Name: " + strName +
                "\n" + "Destination: " + strDestination +
                "\n" + "Date: " + strDate +
                "\n" + "Risks: " + strRisks +
                "\n" + "Description: " + strDescription
        ).setNeutralButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent( AddActivity.this, Adapter.class);
                startActivity(intent);
            }
        }).show();
    }

}