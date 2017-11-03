package com.example.shashimishra.leaveapplication;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashi.Mishra on 1/3/2017.
 */

public class OtpData {
    OtpParser otpparse;
    String url ="http://192.168.0.105:8080/WebServices/otpserv";
    public void getData(String otpinvoke,String email,String name)
    {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("otpinvoke",otpinvoke));
        list.add(new BasicNameValuePair("email",email));
        list.add(new BasicNameValuePair("name",name));
        otpparse.otpparser(list,url);
    }
}
