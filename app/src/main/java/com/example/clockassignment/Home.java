package com.example.clockassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CardView ghadi = findViewById(R.id.CV1);
        CardView Stopwatch = findViewById(R.id.CV3);
        CardView WorldClock = findViewById(R.id.CV2);
        CardView Alarmclock = findViewById(R.id.CV4);
        CardView timer = findViewById(R.id.CV5);
        CardView Weather = findViewById(R.id.CV6);
        ghadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Ghadi.class);
                startActivity(intent);
            }
        });
        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Weather.class);
                startActivity(intent);
            }
        });
        WorldClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Stopwatch.class);
                startActivity(intent);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Timer.class);
                startActivity(intent);
            }
        });
        Alarmclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Alarm.class);
                startActivity(intent);
            }
        });
    }
}