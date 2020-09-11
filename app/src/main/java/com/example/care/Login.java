package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class Login extends AppCompatActivity {
    EditText et1, et2;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et1.getText().toString();
                String pass = et2.getText().toString();
                int check = -1;
                SQLiteDatabase mydatabase = openOrCreateDatabase("tester.dat", MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS userdb(Email VARCHAR, Password VARCHAR, Name VARCHAR, Phone VARCHAR);");
                String sql = "SELECT * FROM userdb";
                Cursor c = mydatabase.rawQuery(sql, null);
                c.moveToFirst();
                if (email.length() == 0 || pass.length() == 0) {
                   check = 2;
                }
                else {
                    while (!c.isAfterLast()) {
                        String e = c.getString(0);
                        String p = c.getString(1);
                        if (e.equals(email) && p.equals(pass)) {
                            check = 1;
                        }
                        c.moveToNext();
                    }
                }
                if(check == 1)
                {
                    Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, City.class);
                    i.putExtra("email",email);
                    et1.setText("");
                    et2.setText("");
                    startActivity(i);
                }
                else if (check == 2)
                {
                    Toast.makeText(Login.this, "Please fill details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    et1.setText("");
                    et2.setText("");
                }
            }
        });
    }
}
