package com.example.shashimishra.leaveapplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.contextClickable;
import static android.R.attr.gravity;
import static android.R.attr.id;
import com.example.shashimishra.leaveapplication.Form_Activity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinalForm1 extends AppCompatActivity {
    String userId, name, T_O_L, T_Date, F_Date;
    String Status, id;
    ProgressBar progressBar;
    private String TAG = FinalForm1.class.getSimpleName();
   ArrayList<HashMap<String,String>> recordList;
    private Context context;
    String sid,sname,leaveT,from,to,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_form1);
        context = this;
        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        recordList = new ArrayList<>();
        recieveData();
    }

    public void recieveData() {
        Intent next = getIntent();
        id = next.getStringExtra("s1");
        new SendData().getData(id);
        new RecievedData().execute();
    }


    private class RecievedData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
        Toast.makeText(FinalForm1.this, "Json Data Is Downloading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            String url = "http://192.168.0.105:8080/WebServices/serv2?user_id=".concat(id);
            String jsonStr = hh.makeServiceCall(url);

            Log.e(TAG, "Response From Url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray list = jsonObj.getJSONArray("Records");


                    //looping through all data
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject l = list.getJSONObject(i);
                        userId = l.getString("s_id");
                        name = l.getString("s_name");
                        T_O_L = l.getString("leave_type");
                        F_Date = l.getString("from_date");
                        T_Date = l.getString("to_date");
                        Status = l.getString("status");

                        //tmp hashmap for single Record
                        HashMap<String,String> record = new HashMap<>();

                        //adding each child node to HashMap key => value
                        record.put("userId",userId);
                        record.put("name",name);
                        record.put("T_O_L",T_O_L);
                        record.put("fDate",F_Date);
                        record.put("tDate",T_Date);
                        record.put("Status",Status);

                        //adding record to recordlist
                        recordList.add(record);
                        //addHeaders();
                    }
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

            } else {
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
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            Toast.makeText(FinalForm1.this,"Json Data Download Complete"+recordList.toString(),Toast.LENGTH_SHORT).show();
            addHeaders();
        }


    }

    //........................................................DISPLAYING ALL THE RECORD IN ARRAYLIST.....................................


    public void addHeaders() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.idMainTable);
        progressBar.setVisibility(View.VISIBLE);
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#B63A07"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"  S id  ", "  S Name  ", "  Leave_Type  ", "  From  ", "  To  ", "  Status  "};
        for (String c : headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setTextColor(Color.parseColor("white"));
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);

            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);
        if(recordList.size()==0)
        {
            Toast.makeText(FinalForm1.this,"No Data In Array",Toast.LENGTH_SHORT).show();
        }
        else {
            for (int i = 0; i<recordList.size(); i++) {
                for (int j = 0; j<recordList.get(i).size(); j++) {

                    sid = recordList.get(i).get("userId");
                    sname = recordList.get(i).get("name");
                    leaveT = recordList.get(i).get("T_O_L");
                    from = recordList.get(i).get("fDate");
                    to = recordList.get(i).get("tDate");
                    status = recordList.get(i).get("Status");
                }
                TableRow row = new TableRow(context);
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                String[] colText = {sid, sname, leaveT, from, to, status};
                for (String text : colText) {
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextColor(Color.parseColor("white"));
                    tv.setTextSize(16);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(text);
                    row.addView(tv);
                }
                tableLayout.addView(row);
            }
        }
        progressBar.setVisibility(View.GONE);

    }


}


