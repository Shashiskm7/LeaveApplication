package com.example.shashimishra.leaveapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Form_Activity extends AppCompatActivity implements OnItemSelectedListener{
    ImageButton IdBackButton;
    Spinner spinnerDropDown;
    ArrayAdapter adapter;
    EditText UserId, UserName;
    ImageButton SendButton;
    ImageButton HistoryButton;
    ProgressBar progressBar;
    TextView FromDateButton;
    TextView ToDateButton;
    String spinneritem;
    Calendar fromDate,toDate,activeDate;//activeDate should be named as activeCalender
    TextView activeDateDisplay;
static  final int DATE_DIALOG_ID = 0;


    //OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        progressBar = (ProgressBar) findViewById(R.id.formProgressBar);
		progressBar.setVisibility(View.visible);
	
        progressBar.setVisibility(View.GONE);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_item, android.R.layout.simple_spinner_item);

        spinnerDropDown = (Spinner) findViewById(R.id.TypeOfLeave);
        spinnerDropDown.setAdapter(adapter);
        spinnerDropDown.setOnItemSelectedListener(Form_Activity.this);
        FromDateButton = (TextView) findViewById(R.id.idFromDate);
        fromDate = Calendar.getInstance();
        FromDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDateDialog(FromDateButton,fromDate);
            }

        });
        //final Calendar c = Calendar.getInstance();
        ToDateButton = (TextView) findViewById(R.id.idToDate);
        toDate = Calendar.getInstance();
        ToDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             showDateDialog(ToDateButton,toDate);
            }
        });

        upDateDisplay(FromDateButton,fromDate);
        upDateDisplay(ToDateButton,toDate);

        initBack();
        initSendButton();
        initHistory();

    }
    public void upDateDisplay(TextView dateDisplay,Calendar date)
    {
        dateDisplay.setText(new StringBuilder().append(date.get(Calendar.DAY_OF_MONTH) + 1).append("-")
                .append(date.get(Calendar.MONTH)).append("-")
                .append(date.get(Calendar.YEAR)).append(""));
    }

    public void showDateDialog(TextView dateDisplay,Calendar date)
    {
       activeDateDisplay = dateDisplay;
       activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            activeDate.set(Calendar.YEAR,year);
            activeDate.set(Calendar.MONTH,month);
            activeDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            upDateDisplay(activeDateDisplay,activeDate);
            unregisteredDateDisplay();
        }
    };
    private void unregisteredDateDisplay()
    {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch(id)//This id played vital role need to think about it
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,dateSetListener,activeDate.get(Calendar.YEAR),
                        activeDate.get(Calendar.MONTH),activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id,Dialog dialog)
    {
super.onPrepareDialog(id, dialog);
        switch(id){
            case DATE_DIALOG_ID:
                ((DatePickerDialog)dialog).updateDate(activeDate.get(Calendar.YEAR),activeDate.get(Calendar.MONTH),activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }


    //Spinner Method
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView spinnerDialogText = (TextView) view;
//        Toast.makeText(this, "You Selected " + spinnerDialogText.getText(), Toast.LENGTH_SHORT).show();
        spinneritem = spinnerDialogText.getText().toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }



    //method for Back Button
    public void initBack() {
        IdBackButton = (ImageButton) findViewById(R.id.idBackButton);
        IdBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent click = new Intent(Form_Activity.this, MainActivity.class);
                startActivity(click);
            }
        });



    }
    //history button
    public void initHistory()
    {
        HistoryButton = (ImageButton) findViewById(R.id.idHistoryButton);
        HistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickhistory = new Intent(Form_Activity.this, FinalForm1.class);
                startActivity(clickhistory);
            }


        });
    }
//send button
    public void initSendButton() {
        SendButton = (ImageButton) findViewById(R.id.idSendButton);
        SendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                UserId = (EditText) findViewById(R.id.idUserID);
                UserName = (EditText) findViewById(R.id.idName);
                String s1 = UserId.getText().toString();
                String s2 = UserName.getText().toString();
                String s3 = spinneritem;
                String dateFrom = (String) ((TextView) findViewById(R.id.idFromDate)).getText();
                String dateTo = (String) ((TextView) findViewById(R.id.idToDate)).getText();
                String s4 = "Unattended";

                new LoginData().getData(s1,s2,s3,dateFrom,dateTo,s4);
                progressBar.setVisibility(View.GONE);
                Intent next = new Intent(Form_Activity.this,FinalForm1.class);
                next.putExtra("s1",s1);
                startActivity(next);

            }

        });

    }




}


