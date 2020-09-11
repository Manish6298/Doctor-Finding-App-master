package com.example.care;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class Patient extends AppCompatActivity {
    Button b1,b2,b3,submit;
    EditText t1,t2,t3,t4;
    EditText t5;
    String email,fees;
    String dr,gender;
    RadioButton rb1, rb2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        email=getIntent().getStringExtra("email");
        fees=getIntent().getStringExtra("fees");
        t1=findViewById(R.id.editView1);
        t2=findViewById(R.id.editView2);
        //t3=findViewById(R.id.editView3);
        t4=findViewById(R.id.editView4);
        t5=findViewById(R.id.editText1);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        dr = getIntent().getStringExtra("dr");
        submit=findViewById(R.id.button1);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        b3=findViewById(R.id.button4);
        LocalDate dateTime =  LocalDate.now() ; // Here 'date' is the current date
        LocalDate d1= dateTime.plusDays(1);
        LocalDate d2=dateTime.plusDays(2);
        LocalDate d3=dateTime.plusDays(3);
        b1.setText(d1.toString());
        b2.setText(d2.toString());
        b3.setText(d3.toString());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5.setText(b1.getText().toString());
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5.setText(b2.getText().toString());
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5.setText(b3.getText().toString());
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gender = t3.getText().toString();
                //submit.setText(gender);
                if((t1.getText().toString().length())<4)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Full Name of Patient",Toast.LENGTH_LONG).show();
                }
                else if((t2.getText().toString().length())>2)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Age of Patient",Toast.LENGTH_LONG).show();
                }
               // else if((t3.getText().toString().equals("MALE")!=true) || (t3.getText().toString().equals("FEMALE")!=true))
                //{
                 //   Toast.makeText(getApplicationContext(),"Please Enter Valid Gender",Toast.LENGTH_LONG).show();

                //}
                else if((t4.getText().length())!=10)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Contact",Toast.LENGTH_LONG).show();

                }
                else if(t5.getText().length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Select a Valid Date",Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(rb1.isChecked())
                    {
                        gender=rb1.getText().toString();
                    }
                    else
                    {
                        gender=rb2.getText().toString();
                    }

                    SQLiteDatabase sql=openOrCreateDatabase("tester.dat",MODE_PRIVATE,null);
                    sql.execSQL("CREATE TABLE IF NOT EXISTS book(pname VARCHAR,page VARCHAR,pgen VARCHAR,pphone VARCHAR,adate VARCHAR,email VARCHAR, dr VARCHAR,fees VARCHAR)");

                    sql.execSQL("CREATE TABLE IF NOT EXISTS Doctorapp(dname VARCHAR,date VARCHAR,count VARCHAR)");
                    Cursor c=sql.rawQuery("SELECT * FROM book WHERE dr='"+dr+"' AND adate='"+t5.getText().toString()+"';",null);
                    if(c.getCount()<=4)
                    {
                        Intent i=new Intent(Patient.this,Confirmation.class);
                        i.putExtra("email",email);
                        i.putExtra("pname",t1.getText().toString());
                        i.putExtra("page",t2.getText().toString());
                        i.putExtra("pgen",gender);
                        i.putExtra("pphone",t4.getText().toString());
                        i.putExtra("adate",t5.getText().toString());
                        i.putExtra("fees",fees);
                        i.putExtra("dr",dr);
                        startActivity(i);
                        finish();
                    }
                    else if(c.getCount()>4)
                    {
                        Toast.makeText(Patient.this,"Doctor is not available.\nPlease select different Date",Toast.LENGTH_LONG).show();
                    }


                }


            }
        });

    }
}
