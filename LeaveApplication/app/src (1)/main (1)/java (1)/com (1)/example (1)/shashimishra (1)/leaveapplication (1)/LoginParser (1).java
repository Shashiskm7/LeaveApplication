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
 * Created by Shashi.Mishra on 12/26/2016.
 */

public class LoginParser {

    public static void loginparse(List<NameValuePair> list, String url) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void employeeParse(List<NameValuePair> list, String url1)
    {
   try{
       DefaultHttpClient httpClient = new DefaultHttpClient();
       HttpPost httpPost = new HttpPost(url1);
       httpPost.setEntity(new UrlEncodedFormEntity(list));
       HttpResponse httpResponse = httpClient.execute(httpPost);
       HttpEntity httpEntity = httpResponse.getEntity();
   }catch (UnsupportedEncodingException e) {
       e.printStackTrace();
   }catch (ClientProtocolException e){
       e.printStackTrace();
   }catch(IOException e){
       e.printStackTrace();
   }
    }
    public static void getparse(List<NameValuePair> list,String url1)
    {
        try{
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url1);
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            httpEntity.getContentLength();
            System.out.print(httpEntity.getContentLength());
        }catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
