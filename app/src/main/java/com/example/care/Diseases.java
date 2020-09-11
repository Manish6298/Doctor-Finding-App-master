package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Diseases extends AppCompatActivity {
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7;
    String c,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);
        c=getIntent().getStringExtra("city");
        email=getIntent().getStringExtra("email");
        iv1 = (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
        iv5 = (ImageView) findViewById(R.id.imageView5);
        iv6 = (ImageView) findViewById(R.id.imageView6);
        iv7 = (ImageView) findViewById(R.id.imageView7);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Cardiologist";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Pulmonologist";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Ophthalmologist";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Diabetologist";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Gynecologist";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Pediatrician";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String c = String.valueOf(getIntent().getStringExtra("city"));
                String s = "Dentist";
                Intent i = new Intent(Diseases.this, List.class);
                i.putExtra("city", c);
                i.putExtra("spec", s);
                i.putExtra("email",email);
                startActivity(i);
            }
        });
    }
}
