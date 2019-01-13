package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.travelmaker.travelmaker.R;

import java.util.Timer;
import java.util.TimerTask;

public class WellcomActivity extends AppCompatActivity {

    Timer timer;


    // Welcome Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcom);

        timer = new Timer();
                timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WellcomActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        },5000);


    }
}
