package com.example.shashimishra.leaveapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Admin_login extends AppCompatActivity {
Button Cancel;
Button Submit;
    EditText UserName;
    EditText Password;
    ProgressBar progressBar1;
    String login;
    String pass;

    public void init()
    {
        Cancel = (Button) findViewById(R.id.idCancel);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent click = new Intent(Admin_login.this,MainActivity.class);
                startActivity(click);
            }
        });

    }
    public void initSubmit()
    {
        Submit = (Button) findViewById(R.id.idSubmit);
        Submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                initialize();
            }

        }

        );
    }
    public void initialize()
    {
         login = UserName.getText().toString();
         pass = Password.getText().toString();
        if(!validate())
        {
            Toast.makeText(this,"Sign up Has failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            onSignupSuccess();
        }
    }
    public void onSignupSuccess()
    {
        progressBar1.setVisibility(View.VISIBLE);

        Intent submitclick = new Intent(Admin_login.this,FinalForm2.class);
        startActivity(submitclick);
        progressBar1.setVisibility(View.GONE);
    }
    public boolean validate()
    {
        boolean valid = true;
        if(login.equals("Omfys") && (pass.equals("omfys")))
        {
            Toast.makeText(this,"Sign up SuccessFull",Toast.LENGTH_SHORT).show();
        }
        else{
            UserName.setError("Wrong UserName");
            Password.setError("Wrong Password");
            valid = false;
        }
        return valid;
    }
//public void validate()
//{
//
//
//    if((login.equals("Omfys")) && (pass.equals("omfys")))
//    {
//        progressBar1.setVisibility(View.VISIBLE);
//        new ExecuteTask1().execute(login,pass);
//    }
//    else{
//        Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT);
//
//    }
//}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        UserName = (EditText) findViewById(R.id.idName);
        Password = (EditText) findViewById(R.id.idPass);
        progressBar1 = (ProgressBar) findViewById(R.id.idLoginProgress);
        progressBar1.setVisibility(View.GONE);
        init();
        initSubmit();

    }


//    class ExecuteTask1 extends AsyncTask<String,String,String>
//    {
//        @Override
//        protected String doInBackground(String... params)
//        {
//            PostData(params);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result)
//        {
//            progressBar1.setVisibility(View.GONE);
//            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
//
//        }
//
//    }
//
//    public void PostData(String[] values)
//    {
//        String s = "";
//        try{
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("");//HTTP POST YET TO BE COMPLETE.
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//            list.add(new BasicNameValuePair("name",values[0]));
//            list.add(new BasicNameValuePair("Pass",values[1]));
//            httpPost.setEntity(new UrlEncodedFormEntity(list));
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//
//            HttpEntity httpEntity = httpResponse.getEntity();
//            s = readResponse(httpResponse);
//
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//
//
//    }
//
//    public String readResponse(HttpResponse res)
//    {
//        InputStream is = null;
//        String return_text = "";
//
//        try{
//            is = res.getEntity().getContent();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String line ="";
//            StringBuffer sb = new StringBuffer();
//
//            while ((line=br.readLine())!=null)
//            {
//                sb.append(line);
//            }
//
//            return_text = sb.toString();
//        }
//        catch (Exception e)
//        {
//
//        }
//        return  return_text;
//    }


}
