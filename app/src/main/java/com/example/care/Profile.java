package com.example.care;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private ImageView i1, i2;
    static final int REQUEST_CALL=1;
    TextView tv1,tv2;
    String name,email;
    Button b;
    String sql;
    Cursor c;
    Float r;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        b = findViewById(R.id.button1);
        String city=getIntent().getStringExtra("city");
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        rb = (RatingBar) findViewById(R.id.ratingBar1);
        name = getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        if(city.equals("d"))
        {
             sql = "SELECT * from caredb where Name='" + name + "';";
        }
        else {
             sql = "SELECT * from care1db where Name='" + name + "';";
        }
        SQLiteDatabase mydatabase = openOrCreateDatabase("tester.dat", MODE_PRIVATE, null);
        c = mydatabase.rawQuery(sql, null);
        c.moveToFirst();
        tv1.setText(c.getString(0));
        String data = c.getString(1)+"\nExp:\t\t"+c.getString(2)+" yrs\n"+c.getString(3)+"\nRs.\t"+c.getString(4)+"\nAddress:\t\t"+c.getString(5);
        tv2.setText(data);
        r = Float.valueOf(c.getString(6));
        rb.setRating(r);
        i1 = (ImageView) findViewById(R.id.imageView2);
        i2 = (ImageView) findViewById(R.id.imageView3);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lati = Double.parseDouble(c.getString(9));
                double lon = Double.parseDouble(c.getString(8));
                String label = name;
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + lati + ">,<" + lon + ">?q=<" + lati + ">,<" + lon + ">(" + label + ")"));
                i.setPackage("com.google.android.apps.maps");
                startActivity(i);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(Profile.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    Activity#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    ActivityCompat.requestPermissions(Profile.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }
                else
                {
                    String t = "tel:+91" + c.getString(7);
                    startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(t)));
                }


            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Profile.this,Patient.class);
                i.putExtra("email",email);
                i.putExtra("dr", name);
                i.putExtra("fees",c.getString(4));
                startActivity(i);
            }
        });
    }
}
