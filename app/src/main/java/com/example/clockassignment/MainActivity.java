package com.example.clockassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Calendar current;
    Spinner spinner;
    TextView timezone, txtCurrentTime, txtTimeZone;
    long milliseconds;
    ArrayAdapter<String> idAdapter;
    SimpleDateFormat sdf;
    Date resultDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        spinner = findViewById(R.id.spinner);

        timezone = findViewById(R.id.timezone);
        txtCurrentTime = findViewById(R.id.txtCurrentTime);
        txtTimeZone = findViewById(R.id.txtTimeZoneTime);

        String [] idArray = TimeZone.getAvailableIDs();
        sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");

        idAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,idArray);
        idAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);
        getGMTtime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID = (String) (parent.getItemAtPosition(position));

                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimeZoneName = timeZone.getDisplayName();
                int TimeZOneOffset = timeZone.getRawOffset();
                int hrs = TimeZOneOffset / 60;
                int mins = TimeZOneOffset % 60;
                milliseconds = milliseconds + timeZone.getRawOffset();

                resultDate = new Date(milliseconds);
                System.out.println(sdf.format(resultDate));
//                timezone.setText(TimeZoneName +":GMT"+hrs+":"+mins);
                txtTimeZone.setText("" + sdf.format(resultDate));
                milliseconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       FloatingActionButton floatingActionButton = findViewById(R.id.dashback);
       floatingActionButton.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
          }
      });
    }

    @SuppressLint("SetTextI18n")
    private void getGMTtime(){
        current = Calendar.getInstance();
        txtCurrentTime.setText("" + current.getTime());
        milliseconds = current.getTimeInMillis();

        TimeZone tzcurrent = current.getTimeZone();
        int offset = tzcurrent.getRawOffset();
        if (tzcurrent.inDaylightTime(new Date())){
            offset = offset + tzcurrent.getDSTSavings();
        }
        milliseconds = milliseconds - offset;
        resultDate = new Date(milliseconds);
        System.out.println(sdf.format(resultDate));
    }
}
