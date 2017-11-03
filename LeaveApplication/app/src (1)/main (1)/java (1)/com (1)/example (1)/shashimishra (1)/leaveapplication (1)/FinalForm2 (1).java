package com.example.shashimishra.leaveapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FinalForm2 extends AppCompatActivity {
    String userId, name, T_O_L, T_Date, F_Date;
    String Status,id;

    ProgressBar progressBar;
    private String TAG = FinalForm1.class.getSimpleName();
    ArrayList<HashMap<String, String>> recordList;
    private Context context;
    String sid, sname, leaveT, from, to, status,id1;
    TableLayout tableLayout;
    TableRow row;
    Button hitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_form2);
        context = this;
        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        recordList = new ArrayList<>();
        recieveData();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void recieveData() {
        new FinalForm2.RecievedData2().execute();
    }


    private class RecievedData2 extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(FinalForm2.this, "Json Data Is Downloading", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler hh = new HttpHandler();
            String url = "http://192.168.0.105:8080/WebServices/serv2";
            String jsonStr = hh.makeServiceCall(url);

            Log.e(TAG, "Response From Url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    recordList.clear();

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray list = jsonObj.getJSONArray("Records");


                    //looping through all data
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject l = list.getJSONObject(i);
                        id = l.getString("id");
                        userId = l.getString("s_id");
                        name = l.getString("s_name");
                        T_O_L = l.getString("leave_type");
                        F_Date = l.getString("from_date");
                        T_Date = l.getString("to_date");
                        Status = l.getString("status");

                        //tmp hashmap for single Record
                        HashMap<String, String> record = new HashMap<>();

                        //adding each child node to HashMap key => value
                        record.put("id",id);
                        record.put("userId", userId);
                        record.put("name", name);
                        record.put("T_O_L", T_O_L);
                        record.put("fDate", F_Date);
                        record.put("tDate", T_Date);
                        record.put("Status", Status);

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
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(FinalForm2.this, "Json Data Download Complete", Toast.LENGTH_SHORT).show();
            addHeaders();
        }
    }

    //........................................................DISPLAYING ALL THE RECORD IN ARRAYLIST.....................................


    public void addHeaders(){
        tableLayout = (TableLayout) findViewById(R.id.idMainTable);
        progressBar.setVisibility(View.VISIBLE);
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#B63A07"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"  S.no   ","  S id  ", "  S Name  ", "  Leave_Type  ", "  From  ", "  To  ", "  Status  "};
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
        if (recordList.size() == 0) {
            Toast.makeText(FinalForm2.this, "No Data In Array", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < recordList.size(); i++) {

                for (int j = 0; j < recordList.get(i).size(); j++) {
                     id1 = recordList.get(i).get("id");
                    sid = recordList.get(i).get("userId");
                    sname = recordList.get(i).get("name");
                    leaveT = recordList.get(i).get("T_O_L");
                    from = recordList.get(i).get("fDate");
                    to = recordList.get(i).get("tDate");
                    status = recordList.get(i).get("Status");
                }
                 row = new TableRow(context);
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                final String[] colText = {id1, sid, sname, leaveT, from, to, status};
                for (String text : colText) {
                    if(text!=colText[6]) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextColor(Color.parseColor("white"));
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);
                    }
                    else {
                        //Getting null value at status coz of which status is not being set......................................
                            hitButton = new Button(this);
                            hitButton.setText(colText[6]);
                            hitButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
//                            hitbutton.setId(mystatusbutton);
                            hitButton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                    alertDialogBuilder.setTitle("Approval");
                                    alertDialogBuilder.setMessage("Do You Want To Approve To This Request").setCancelable(true)
                                            .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                                                public void onClick(DialogInterface dialogInterface,int which)
                                                {
                                                  String approve = "Accepted";
//                                                    hitButton.setText("Accepted");

                                                    new UpdateData().getData(approve,colText[0]);
                                                    tableLayout.findViewById(R.id.idMainTable);
                                                    tableLayout.removeAllViewsInLayout();
                                                    progressBar.setVisibility(View.VISIBLE);
                                                    progressBar.setVisibility(View.GONE);
                                                    recieveData();

                                                }
                                            }).setNegativeButton("No",new DialogInterface.OnClickListener(){
                                       public void onClick(DialogInterface dialogInterface,int which)
                                       {
                                           String approve = "NotAccepted";
//                                           hitButton.setText("Not Accepted");
                                           new UpdateData().getData(approve,colText[0]);
                                           tableLayout.findViewById(R.id.idMainTable);
                                           tableLayout.removeAllViewsInLayout();
                                           row.removeAllViews();
                                           progressBar.setVisibility(View.VISIBLE);
                                           recieveData();
                                           progressBar.setVisibility(View.GONE);
                                       }
                                    });

                                    //create alert dialog
                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                    // show it
                                    alertDialog.show();
                                }
                            });
                            row.addView(hitButton);
                    }
                }
                tableLayout.addView(row);

            }
            progressBar.setVisibility(View.GONE);

        }


    }
}



