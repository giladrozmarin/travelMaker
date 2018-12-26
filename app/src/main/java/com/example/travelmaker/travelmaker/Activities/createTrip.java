package com.example.travelmaker.travelmaker.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.travelmaker.travelmaker.Models.Trip;
import com.example.travelmaker.travelmaker.R;
import com.google.firebase.database.FirebaseDatabase;

public class createTrip extends AppCompatActivity {


    //android layout

    private EditText tripName,location;
    private EditText startDay,endDay;
    private Button creatTrip_btn;
    private ProgressBar loadingProgress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
       //var = id layout
        tripName = findViewById(R.id.tripName);
        location = findViewById(R.id.city);
        startDay = findViewById(R.id.startDate);
        endDay = findViewById(R.id.endDate);
        creatTrip_btn = findViewById(R.id.creatTrip1);
        loadingProgress = findViewById(R.id.regProgressBar1);
        loadingProgress.setVisibility(View.INVISIBLE);

        creatTrip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //invisible btn
                creatTrip_btn.setVisibility((View.INVISIBLE));
                //visible btn
                loadingProgress.setVisibility(View.VISIBLE);

                final String tripname = tripName.getText().toString();
                final String location1 = location.getText().toString();
                final String start = startDay.getText().toString();
                final String end = endDay.getText().toString();

                // after we created trip we need to update
                Trip trip = new Trip(tripname,location1,start,end);

                FirebaseDatabase.getInstance().getReference("Trips").child(tripname).setValue(trip);

            }


        });




    }
}
