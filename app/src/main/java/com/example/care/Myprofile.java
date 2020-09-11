package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Myprofile extends AppCompatActivity {
    TextView t1;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        email=getIntent().getStringExtra("email");
        String email=getIntent().getStringExtra("email");
        t1=(TextView) findViewById(R.id.textView2);
        SQLiteDatabase sql=openOrCreateDatabase("tester.dat",MODE_PRIVATE,null);
        t1.setText(email);
        Cursor c=sql.rawQuery("SELECT * FROM userdb WHERE Email='"+email+"';",null);
        c.moveToFirst();
        String prof="\nName:    "+c.getString(2)+"\n\nEmail:    "+c.getString(0)+"\n\nMobile:    "+c.getString(3);
        t1.setText(prof);
    }
}
