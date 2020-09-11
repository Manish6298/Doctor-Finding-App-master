package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.nio.file.Files;

public class Sign extends AppCompatActivity {
    private EditText et1, et2, et3, et4;
    private Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        bt1 = (Button) findViewById(R.id.button1);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase mydatabase;
                mydatabase = openOrCreateDatabase("tester.dat", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS userdb(Email VARCHAR, Password VARCHAR, Name VARCHAR, Phone VARCHAR);");
                Cursor cursor;
                String email = et1.getText().toString();
                String pass = et2.getText().toString();
                String name = et3.getText().toString();
                String phone = et4.getText().toString();
                cursor=mydatabase.rawQuery("SELECT * FROM userdb WHERE email='"+email+"';",null);
                char c,s;
                int ac=0,ad=0;
                for(int i=0;i<email.length();i++)
                {
                    char cu=email.charAt(i);
                    if(cu=='@')
                    {
                        c='@';
                        ac++;
                    }
                    else if(cu=='.')
                    {
                        s='.';
                        ad++;
                    }
                }

                if(email.length()==0 || pass.length()==0 || name.length()==0|| phone.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter all the Fields", Toast.LENGTH_LONG).show();

                }
                else if (ac!=1 || ad!=1)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email", Toast.LENGTH_LONG).show();

                }
                else if(pass.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_LONG).show();

                }
                else if(pass.length()<4)
                {
                    Toast.makeText(getApplicationContext(), "Password Should be atleast 4 characters", Toast.LENGTH_LONG).show();

                }
                else if(name.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_LONG).show();

                }
                else if(phone.length()!=10)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Phone Number", Toast.LENGTH_LONG).show();

                }
                else if(cursor.getCount()>0)
                {
                    Toast.makeText(getApplicationContext(), "User Already Registered Please Login", Toast.LENGTH_LONG).show();

                }
                else {
                    //SQLiteDatabase mydatabase = openOrCreateDatabase("tester.dat", MODE_PRIVATE, null);
                    //mydatabase.execSQL("CREATE TABLE IF NOT EXISTS userdb(Email VARCHAR, Password VARCHAR, Name VARCHAR, Phone VARCHAR);");
                    mydatabase.execSQL("INSERT INTO userdb values('" + email + "', '" + pass + "', '" + name + "', '" + phone + "')");
                    Toast.makeText(getApplicationContext(), "Registered succesfully", Toast.LENGTH_LONG).show();
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");
                    Intent i = new Intent(Sign.this, Start.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
