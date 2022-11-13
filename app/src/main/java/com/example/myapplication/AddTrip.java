package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
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

public class AddTrip extends AppCompatActivity {
    EditText Destination_input, Name_input, Description_input;
    Button Save_button;
    RadioGroup Risks_input;
    RadioButton radioChecked;
    RadioButton checkYesAdd, checkNoAdd;
    TextView Date_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        Date_input = findViewById(R.id.Date_input);
        Name_input = findViewById(R.id.Name_input);
        Destination_input = findViewById(R.id.Destination_input);
        Risks_input = findViewById(R.id.Risks_input);
        Description_input = findViewById(R.id.Description_input);
        checkYesAdd = findViewById(R.id.checkYesAdd);
        checkNoAdd = findViewById(R.id.checkNoAdd);
        Save_button = findViewById(R.id.Save_button);
        Save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getInputs();
                if (checkTrip()) {
                    Database myDB = new Database(AddTrip.this);
                    int checkedID = Risks_input.getCheckedRadioButtonId();
                    radioChecked = findViewById(checkedID);
                    String textRadio = radioChecked.getText().toString().trim();

                    // thứ tự
                    myDB.addDataTrip(Name_input.getText().toString().trim(),
                            Destination_input.getText().toString().trim(),
                            Date_input.getText().toString().trim(),
                            textRadio,
                            Description_input.getText().toString().trim());

                    // show
                    String strName = Name_input.getText().toString();
                    String strDestination = Destination_input.getText().toString();
                    String strDate = Date_input.getText().toString();
                    String strRisks = radioChecked.getText().toString().trim();
                    String strDescription = Description_input.getText().toString();

                    displayNextAlert(strName, strDestination, strDate, strRisks, strDescription);
                } else {
                    Toast.makeText(AddTrip.this, "Please enter enough information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkTrip() {
        if (Name_input.getText().toString().length() == 0) {
            return false;
        }
        if (!checkYesAdd.isChecked() && !checkNoAdd.isChecked() ) {
            return false;
        }
        if (Destination_input.getText().toString().length() == 0) {
            return false;
        }
        if (Date_input.getText().toString().length() == 0) {
            return false;
        }
        if (Description_input.getText().toString().length() == 0) {
            return false;
        }
        return true;
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DateOfAdd();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB(LocalDate dob) {
        TextView dobText = (TextView) findViewById(R.id.Date_input);
        dobText.setText(dob.toString());
    }

    // show entered data

    private void displayNextAlert(String strName, String strDestination, String strDate, String strRisks, String strDescription) {
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage(
                "\n" + "Name: " + strName +
                        "\n" + "Destination: " + strDestination +
                        "\n" + "Date: " + strDate +
                        "\n" + "Risks: " + strRisks +
                        "\n" + "Description: " + strDescription
        ).setNeutralButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(AddTrip.this, MainActivity.class);
                startActivity(intent);
            }
        }).show();

    }

}