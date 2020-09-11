package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class City extends AppCompatActivity {
    ImageView i1,i2,i3;
    String emaili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        emaili=getIntent().getStringExtra("email");
        i1=(ImageView)findViewById(R.id.imageView1);
        i2=(ImageView) findViewById(R.id.imageView2);
        i3=(ImageView)findViewById(R.id.imageView3);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(City.this,Diseases.class);
                String v="d";
                i.putExtra("city",v);
                i.putExtra("email",emaili);
                startActivity(i);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(City.this,Diseases.class);
                String v1="m";
                i.putExtra("city",v1);
                i.putExtra("email",emaili);
                startActivity(i);
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(City.this,Settings.class);
                i.putExtra("email",emaili);
                startActivity(i);
                finish();
            }
        });
    }
}
