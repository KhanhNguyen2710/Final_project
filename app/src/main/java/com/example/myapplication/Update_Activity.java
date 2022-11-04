package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class Update_Activity extends AppCompatActivity {

    EditText Destination_input, Name_input, Description_input;
    Button Save_button;
    RadioGroup Risks_input;
    RadioButton radioChecked;
    TextView Date_input;
    String trip_id, name, destination, date, risks, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Name_input = findViewById(R.id.Name_input2);
        Destination_input = findViewById(R.id.Destination_input2);
        Date_input = findViewById(R.id.Date_input2);
        Risks_input = findViewById(R.id.Risks_input2);
        Description_input = findViewById(R.id.Description_input2);
        /*int checkedID2 = Risks_input.getCheckedRadioButtonId();
        radioChecked = findViewById(checkedID2);*/
        // String radioText = radioChecked.getText().toString();


        Save_button=findViewById(R.id.Save_button2);

        getAndSetIntentData();

        Save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedID2 = Risks_input.getCheckedRadioButtonId();
                radioChecked = findViewById(checkedID2);
                String radioText = radioChecked.getText().toString();

                Database myDB = new Database(Update_Activity.this);
                name = Name_input.getText().toString().trim();
                destination = Destination_input.getText().toString().trim();
                date = Date_input.getText().toString().trim();
                risks = radioText;
                description = Description_input.getText().toString().trim();
                myDB.updateData(trip_id, name, destination, date, risks, description);
            //risks = radioText;



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


    public void showDatePickerDialog2(View v){
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void updateDOB2(LocalDate dob){
        TextView dobText = (TextView) findViewById(R.id.Date_input2);
        dobText.setText(dob.toString());
    }
}