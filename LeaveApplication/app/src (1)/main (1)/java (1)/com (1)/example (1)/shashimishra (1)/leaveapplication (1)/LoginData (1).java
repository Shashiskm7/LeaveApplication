package com.example.shashimishra.leaveapplication;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashi.Mishra on 12/26/2016.
 */

public class LoginData {
    LoginParser loginparse;
    String url = "http://192.168.0.105:8080/WebServices/serv1";
    String url1="http://192.168.0.105:8080/WebServices/register";
    public void getData(String s1,String s2,String s3, String dateFrom,String dateTo,String s4)
    {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("user_id", s1));
            list.add(new BasicNameValuePair("user_name", s2));
            list.add(new BasicNameValuePair("Type_of_leave",s3));
            list.add(new BasicNameValuePair("From_date",dateFrom));
            list.add(new BasicNameValuePair("To_date",dateTo));
            list.add(new BasicNameValuePair("Status",s4));
         loginparse.loginparse(list,url);
     }
    public void getEmployeeData(String id,String name,String email,String pno)
    {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("eid",id));
        list.add(new BasicNameValuePair("ename",name));
        list.add(new BasicNameValuePair("email",email));
        list.add(new BasicNameValuePair("pno",pno));
        loginparse.employeeParse(list,url1);
    }

    public void getEid(String id,String name,String email)
    {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("eid",id));
        list.add(new BasicNameValuePair("name",name));
        list.add(new BasicNameValuePair("email",email));
        loginparse.getparse(list,url1);
    }
}
