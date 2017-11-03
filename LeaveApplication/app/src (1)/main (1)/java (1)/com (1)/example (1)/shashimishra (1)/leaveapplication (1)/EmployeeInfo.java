package com.example.shashimishra.leaveapplication;

/**
 * Created by Shashi.Mishra on 1/9/2017.
 */

public class EmployeeInfo {
    private int eid;
    private String ename;
    private String email;
    private long enumber;

    public EmployeeInfo(int eid,String ename,String email,long enumber)
    {
        this.eid = eid;
        this.ename = ename;
        this.email = email;
        this.enumber = enumber;
    }


    public void setEid(int eid)
    {
        this.eid = eid;
    }
    public int getEid()
    {
        return eid;
    }
    public void setEname(String ename)
    {
        this.ename = ename;
    }
    public String getEname()
    {
        return ename;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEnumber(int enumber)
    {
        this.enumber = enumber;
    }
    public long getEnumber()
    {
        return enumber;
    }
}
