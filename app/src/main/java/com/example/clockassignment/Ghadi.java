package com.example.clockassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

public class Ghadi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghadi);
        Clock clock = findViewById(R.id.fortost);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentdate = java.text.DateFormat.getTimeInstance().format(new Date());
                Toast.makeText(getApplicationContext(),currentdate,Toast.LENGTH_LONG).show();
            }
        });
        FloatingActionButton dashback = findViewById(R.id.dashback);
        dashback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Ghadi.this,Home.class);
                startActivity(i);
            }
        });
    }

}