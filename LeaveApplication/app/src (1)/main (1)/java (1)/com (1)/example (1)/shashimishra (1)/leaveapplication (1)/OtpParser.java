package com.example.shashimishra.leaveapplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Shashi.Mishra on 1/3/2017.
 */

public class OtpParser {
    public static void otpparser(List<NameValuePair> list,String url)
    {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity =  httpResponse.getEntity();
        }
        catch(UnsupportedEncodingException e)
        {e.printStackTrace();}
        catch(ClientProtocolException e)
        {e.printStackTrace();}
        catch(IOException e)
        {e.printStackTrace();}
    }
}
