package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePass extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        email=getIntent().getStringExtra("email");
        e1=findViewById(R.id.editText1);
        e2=findViewById(R.id.editText2);
        e3=findViewById(R.id.editText3);
        b = findViewById(R.id.button4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sql=openOrCreateDatabase("tester.dat",MODE_PRIVATE,null);
                Cursor c=sql.rawQuery("SELECT * FROM userdb WHERE Email='"+email+"';",null);
                c.moveToFirst();
                String dcurrentp=c.getString(1);
                String ecurrentp=e1.getText().toString();
                String npass=e2.getText().toString();
                String cnpass=e3.getText().toString();
                if(ecurrentp.equals(dcurrentp)==false)
                {
                    Toast.makeText(getApplicationContext(),"Old Password Not Correct",Toast.LENGTH_LONG).show();
                }
                else if(npass.equals(cnpass)==false)
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Same New Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    sql.execSQL("UPDATE userdb set Password='"+cnpass+"' where email='"+email+"'");
                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ChangePass.this, Start.class);
                    startActivity(i);
                    finish();

                }

            }
        });



    }
}
