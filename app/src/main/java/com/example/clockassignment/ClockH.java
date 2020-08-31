package com.example.clockassignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class ClockH extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_h);
        Clock clock = findViewById(R.id.fortost);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentdate = java.text.DateFormat.getTimeInstance().format(new Date());
                Toast.makeText(getApplicationContext(),currentdate,Toast.LENGTH_LONG).show();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ClockH.this,Home.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}