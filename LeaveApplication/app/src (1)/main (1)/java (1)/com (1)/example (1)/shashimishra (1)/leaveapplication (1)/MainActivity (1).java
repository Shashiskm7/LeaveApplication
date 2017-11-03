package com.example.shashimishra.leaveapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
  Button Userbut;
  Button Adminbut;
    public void init()
    {
        Userbut =(Button)findViewById(R.id.Userbut);

        Userbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,RegistrationForm.class);
                startActivity(toy);
            }
        });
    }

    public void init2()
    {
        Adminbut = (Button)findViewById(R.id.Adminbut);

        Adminbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy1 = new Intent(MainActivity.this,Admin_login.class);
                startActivity(toy1);
            }
        });
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        init2();
    }

}
