package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAppointments extends AppCompatActivity {
    ListView l1;
    Cursor c;
    String name[];
    String info[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);
        l1=findViewById(R.id.listView1);
        String email=getIntent().getStringExtra("email");
        SQLiteDatabase sql=openOrCreateDatabase("tester.dat",MODE_PRIVATE,null);
        try {
            c = sql.rawQuery("SELECT * FROM book WHERE email='" + email + "';", null);
            c.moveToFirst();
            ArrayList<String> aa = new ArrayList<String>();
            ArrayList<String> aa1 = new ArrayList<String>();
            while (!c.isAfterLast()) {
                aa.add(c.getString(6));
                aa1.add("Patient Name:\t" + c.getString(0) + "\nDate:\t" + c.getString(4) + "\nFees:\t" + c.getString(7));
                c.moveToNext();
            }
            name = aa.toArray(new String[aa.size()]);
            info = aa1.toArray(new String[aa1.size()]);
            ListSec ll = new ListSec(this, name, info);
            l1.setAdapter(ll);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No Bookings Available",Toast.LENGTH_LONG).show();
        }
    }
}
