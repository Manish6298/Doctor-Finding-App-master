package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ListView li1;
    String email;
    Cursor c;
    String name[];
    String desc[];
    String rat[];
    String exp[];
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        li1=(ListView) findViewById(R.id.listView1);
        SQLiteDatabase sql=openOrCreateDatabase("tester.dat",MODE_PRIVATE,null);
         city=getIntent().getStringExtra("city");
        String spec=getIntent().getStringExtra("spec");
        email=getIntent().getStringExtra("email");
        if (city.equals("d"))
        {
            String s="SELECT * FROM caredb WHERE Specialization='"+spec+"';";
            c=sql.rawQuery(s,null);
        }
        else if(city.equals("m"))
        {
            String s="SELECT * FROM care1db WHERE Specialization='"+spec+"';";
            c=sql.rawQuery(s,null);
        }
        //String state="SELECT * FROM caredb";
        //c=sql.rawQuery(state,null);
        c.moveToFirst();
        ArrayList<String> aa=new ArrayList<String>();
        ArrayList<String> aa1=new ArrayList<String>();
        ArrayList<String> aa2=new ArrayList<String>();
        ArrayList<String> aa3=new ArrayList<String>();
        while(!c.isAfterLast())
        {
            aa.add(c.getString(0));
            aa1.add(c.getString(1)+"\n"+c.getString(3));
            aa2.add("\n"+c.getString(6));
            aa3.add("Exp: "+c.getString(2)+"yrs");
            c.moveToNext();
        }

        name=aa.toArray(new String[aa.size()]);
        desc=aa1.toArray(new String[aa1.size()]);
        rat=aa2.toArray(new String[aa2.size()]);
        exp=aa3.toArray(new String[aa3.size()]);
        CustomList custom=new CustomList(this,name,desc,rat,exp);
        li1.setAdapter(custom);
        li1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nxt=new Intent(List.this,Profile.class);
                nxt.putExtra("name",name[i]);
                nxt.putExtra("email",email);
                nxt.putExtra("city",city);
                startActivity(nxt);
            }
        });


    }
}
