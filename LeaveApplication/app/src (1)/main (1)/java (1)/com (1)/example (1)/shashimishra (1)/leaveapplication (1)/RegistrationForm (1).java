package com.example.shashimishra.leaveapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AsyncListUtil;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class RegistrationForm extends AppCompatActivity {

    Button goButton;
    EditText userId,username,emailId,phone;
    String id,name,email,pno;
    Boolean b;
    private String TAG = RegistrationForm.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        goButton = (Button) findViewById(R.id.GoButton);
        initGo();
    }

    public void initGo()
    {
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
            }
        });
    }
    public void initialize()
    {
        userId = (EditText) findViewById(R.id.idueseridEditText);
        username = (EditText)findViewById(R.id.idusernameField);
        emailId = (EditText)findViewById(R.id.idemailIdField);
        phone = (EditText)findViewById(R.id.idPhoneField);
        id = userId.getText().toString();
        name = username.getText().toString();
        email = emailId.getText().toString();
        pno = phone.getText().toString();

        if(!validate())
        {
            Toast.makeText(this,"Sign up has failed Try again",Toast.LENGTH_SHORT);
        }
        else{
            String otpinvoke = "invoke";
            new LoginData().getEid(id,name,email);
                 new RegistrationForm.FetchRecord().execute();
            if(b==Boolean.TRUE)
            {
                userId.setError("User Already Registered....");
                username.setError("User Already Registered....");
                emailId.setError("User Already Registered....");
                phone.setError("User Already Registered....");
            }
            else {
            Intent go = new Intent(RegistrationForm.this,OTPActivity.class);
            go.putExtra("id",id);
            go.putExtra("name",name);
            go.putExtra("email",email);
            go.putExtra("pno",pno);
            go.putExtra("otpinvoke",otpinvoke);
            startActivity(go);
            }
        }
    }
    public boolean validate()
    {
        Boolean valid;
        if((id.equals("") || id==null)||(name.equals("")|| name==null)||(email.equals("")||email==null)||(pno.equals("")|| pno==null))
        {
            userId.setError("Field Cannot be blank");
            username.setError("Field Cannot be blank");
            emailId.setError("Field Cannot be blank");
            phone.setError("Field Cannot be blank");
            valid = false;
        }
        else{
            valid = true;
        }
            return valid;
    }

    private class FetchRecord extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpHandler hh = new HttpHandler();
            String url = "http://10.0.0.105:8080/WebServices/register?eid="+id+"&ename="+name+"&email="+email;
            String JsonStr = hh.makeServiceCall(url);

            if(JsonStr!=null)
            {
                try{
                    JSONObject job = new JSONObject(JsonStr);
                     b = job.getBoolean("result");
                }catch (final JSONException e) {
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
    }



}
