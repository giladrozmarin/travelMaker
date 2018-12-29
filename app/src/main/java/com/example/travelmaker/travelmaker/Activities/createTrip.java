package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.travelmaker.travelmaker.Models.Trip;
import com.example.travelmaker.travelmaker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class createTrip extends AppCompatActivity {

    //android layout
    private EditText tripName,location;
    private EditText startDay,endDay;
    private Button creatTrip_btn;
    private ProgressBar loadingProgress;

    //Get curent user
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


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
                        final String guide = mAuth.getCurrentUser().getUid();
                        final String end = endDay.getText().toString();


                        Trip trip = new Trip(tripname, location1, start,end,guide);

                        FirebaseDatabase.getInstance().getReference("Trips").child(tripname).setValue(trip);
                        FirebaseDatabase.getInstance().getReference("travelers in tripid").child(tripname).setValue("");

                        showMassge("Trip creat successfully");
                        updateUI();

                    }


                });

    }
    /*
    private void updateTravelers(final String tripname,final String location1,final String start,final String guide,final String end,final String travelers){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Trips");

        Map<String,Object> trip = new HashMap<>();

        traveler.put("location1",location1);
        traveler.put("start",start);
        traveler.put("guide",guide);
        traveler.put("end",end);
        traveler.put("travelers",travelers);

        myRef.child(tripname).updateChildren(traveler).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete())
                    showMassge("traveler updated");
            }
        });

*/


    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }

}
