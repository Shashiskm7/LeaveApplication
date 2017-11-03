package com.example.shashimishra.leaveapplication;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashi.Mishra on 12/29/2016.
 */

public class UpdateData {
    UpdateParser updateParse;
    String url="http://192.168.0.105:8080/WebServices/serv3";

    public void getData(String approve,String id)
    {
        List<NameValuePair> list1 = new ArrayList<NameValuePair>();
        list1.add(new BasicNameValuePair("id",id));
        list1.add(new BasicNameValuePair("status",approve));
        updateParse.updateParse(list1,url);
    }
}
