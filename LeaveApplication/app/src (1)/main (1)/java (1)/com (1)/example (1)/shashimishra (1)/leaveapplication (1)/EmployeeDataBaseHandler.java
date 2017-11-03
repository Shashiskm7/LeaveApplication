package com.example.shashimishra.leaveapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shashi.Mishra on 1/9/2017.
 */

public class EmployeeDataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmployeeCollection";
    private static final String TABLE_EMPLOYEE = "Employees";
    private static final String KEY_ID =  "eid";
    private static final String KEY_NAME = "name";
    private static final String KEY_MAIL = "email";
    private static final String KEY_PH_NO = "ph_number";

    public EmployeeDataBaseHandler(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE "+TABLE_EMPLOYEE+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_MAIL+" TEXT,"+KEY_PH_NO+" LONG"+")";
           db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_EMPLOYEE);
        onCreate(db);
    }

    void addEmployee(EmployeeInfo info)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID,info.getEid());
        values.put(KEY_NAME,info.getEname());
        values.put(KEY_MAIL,info.getEmail());
        values.put(KEY_PH_NO,info.getEnumber());

        db.insert(TABLE_EMPLOYEE,null,values);
        db.close();
    }
    EmployeeInfo getEmployeeinfo(int id)
    {
     SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EMPLOYEE, new String[]{KEY_ID,KEY_NAME,KEY_MAIL,KEY_PH_NO},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
    if(cursor!=null)
    {
        cursor.moveToFirst();
    }
        EmployeeInfo info = new EmployeeInfo(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
        return info;
    }
    public int updateEmployee(EmployeeInfo info)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,info.getEname());
        values.put(KEY_MAIL,info.getEmail());
        values.put(KEY_PH_NO,info.getEnumber());

        return db.update(TABLE_EMPLOYEE,values,KEY_ID+"=?",new String[]{String.valueOf(info.getEid())});

    }

    public void deleteEmployee(EmployeeInfo info)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE,KEY_ID+"=?",new String[]{String.valueOf(info.getEid())});
    db.close();
    }
}
