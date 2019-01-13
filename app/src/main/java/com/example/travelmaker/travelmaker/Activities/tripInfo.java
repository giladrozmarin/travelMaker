package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelmaker.travelmaker.Models.Trip;
import com.example.travelmaker.travelmaker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tripInfo extends AppCompatActivity {

    //android layout
    private TextView tripName,location,startDay,endDay;;
    private Button back;

    String data;
    String dataguide;
    Trip trip2;
    //Get my db
    FirebaseDatabase db;
    DatabaseReference db_ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_info);



        //get my db
        db = FirebaseDatabase.getInstance();
        ;
         data = getIntent().getExtras().getString("Trip","defaultKey");
         dataguide = getIntent().getExtras().getString("guide","defaultKey");


        //
        tripName = findViewById(R.id.textView21);
        location = findViewById(R.id.textView22);
        startDay = findViewById(R.id.textView23);
        endDay = findViewById(R.id.textView25);
        back = findViewById(R.id.button);



        db_ref = db.getReference("Trips").child(dataguide+" "+data);

        db_ref.child(data).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                trip2 = snapshot.getValue(Trip.class);


                final String location1 = trip2.getLocation();
                final String start = trip2.getStart_date();
                final String end = trip2.getEnd_date();
                tripName.setText(data);
                location.setText(location1);
                startDay.setText(start);
                endDay.setText(end);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regTrip = new Intent(tripInfo.this, regTrip.class);
                regTrip.putExtra("Trip",data);
                startActivity(regTrip);
                finish();

            }
        });


    }
    private void updateUI() {

        Intent regTrip = new Intent(tripInfo.this, regTrip.class);
        startActivity(regTrip);
        finish();
    }

}
