package com.example.shashimishra.leaveapplication;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashi.Mishra on 12/27/2016.
 */

public class SendData {
    SendParser sendparse;
    String url = "http://192.168.0.105:8080/WebServices/serv2";
    public void getData(String id)
    {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("user_id", id));
        sendparse.sendparse(list,url);
    }
}
