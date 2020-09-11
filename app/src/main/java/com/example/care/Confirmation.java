package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {
    TextView t1;
    Button b1;
    String pname,page,pgen,pphone,adate,email;
    String dr,fees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        pname=getIntent().getStringExtra("pname");
        page=getIntent().getStringExtra("page");
        pgen=getIntent().getStringExtra("pgen");
        pphone=getIntent().getStringExtra("pphone");
        adate=getIntent().getStringExtra("adate");
        dr = getIntent().getStringExtra("dr");
        fees=getIntent().getStringExtra("fees");
        String conformation="\n\nName:    "+pname+"\n\nAge:    "+page+"\n\nGender:    "+pgen+"\n\nPhone:    "+pphone+"\n\nAppointment Date:     "+adate+"\n\nFees:     "+fees;
        b1=findViewById(R.id.button1);
         email=getIntent().getStringExtra("email");

        t1=findViewById(R.id.textView2);
        t1.setText(conformation);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sql=openOrCreateDatabase("tester.dat",MODE_PRIVATE,null);
                sql.execSQL("CREATE TABLE IF NOT EXISTS book(pname VARCHAR,page VARCHAR,pgen VARCHAR,pphone VARCHAR,adate VARCHAR,email VARCHAR, dr VARCHAR,fees VARCHAR)");
                //sql.execSQL("CREATE TABLE IF NOT EXISTS drcount(dname VARCHAR,date VARCHAR,Count Integer);");

                sql.execSQL("INSERT INTO book values('"+pname+"','"+page+"','"+pgen+"','"+pphone+"','"+adate+"','"+email+"','"+dr+"','"+fees+"');");
                //sql.execSQL("INSERT INTO drcount('"+dr+"','"+adate+"','"++"')");
                Intent i=new Intent(Confirmation.this,Booked.class);
                i.putExtra("email",email);
                startActivity(i);
                finish();
            }
        });

    }
}
