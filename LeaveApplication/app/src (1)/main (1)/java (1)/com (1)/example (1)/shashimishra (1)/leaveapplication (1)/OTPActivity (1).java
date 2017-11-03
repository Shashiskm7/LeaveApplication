package com.example.shashimishra.leaveapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class OTPActivity extends AppCompatActivity {
    String id, name, email, pno, otpinvoke, otp, otpEntered;
    private String TAG = OTPActivity.class.getSimpleName();
    EditText otpField;
    Button Submit;
    ProgressBar progressBar;
    private Context context;
    EmployeeDataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;
        otpField = (EditText)findViewById(R.id.otpCompareField);
        progressBar = (ProgressBar)findViewById(R.id.progressBar123);
        receiveOTP();
        progressBar.setVisibility(View.GONE);
        db = new EmployeeDataBaseHandler(this);
        initSubmitButton();
    }


    public void receiveOTP() {
        Intent go = getIntent();
        id = go.getStringExtra("id");
        name = go.getStringExtra("name");
        email = go.getStringExtra("email");
        pno = go.getStringExtra("pno");
        otpinvoke = go.getStringExtra("otpinvoke");
        new OTPActivity.ReceivedOTP().execute();
    }

    private class ReceivedOTP extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(OTPActivity.this, "Processing", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            String url = "http://10.0.0.105:8080/WebServices/otpserv?otpinvoke=" + otpinvoke + "&email=" + email + "&name=" + name;
            String jsonStr = hh.makeServiceCall(url);

            Log.e(TAG, "Response From Url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    otp = jsonObj.getString("otp");
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error1: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    });
                }
            }
            else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

        return null;
        }

        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            Toast.makeText(OTPActivity.this,"OTP Fetched "+otp,Toast.LENGTH_SHORT).show();
        }
    }

    public void initSubmitButton()
    {
      otpEntered = otpField.getText().toString();
        Submit = (Button)findViewById(R.id.idotpsubmit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpEntered = otpField.getText().toString();
                if(otpEntered.equals(otp))
                {
                     Log.d("Insert:","Inserting..");
                    db.addEmployee(new EmployeeInfo(Integer.parseInt(id),name,email,Long.parseLong(pno)));
                    EmployeeInfo info = db.getEmployeeinfo(Integer.parseInt(id));
                    Log.d("Display",info.getEname());
                     info.getEid();
                    info.getEname();
                    info.getEmail();
                    info.getEnumber();
                    new LoginData().getEmployeeData(id,info.getEname(),info.getEmail(),pno);
                    Intent next = new Intent(OTPActivity.this,Form_Activity.class);
                    startActivity(next);
                }
                else{
                       otpField.setError("OTP do not match");
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Resend OTP");
                    alertDialogBuilder.setMessage("OTP Mismatch!..").setCancelable(true);

                }
            }
        });

    }




}
