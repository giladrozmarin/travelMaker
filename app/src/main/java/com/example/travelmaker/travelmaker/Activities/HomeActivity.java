package com.example.travelmaker.travelmaker.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.travelmaker.travelmaker.Models.Trip;
import com.example.travelmaker.travelmaker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private DatabaseReference tripDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
//        if(this.)

        tripDB = FirebaseDatabase.getInstance().getReference("/Trips");
        // My top posts by number of stars
        tripDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot tripSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    System.out.println("child: key: " + tripSnapshot.getKey() + ", value: " + tripSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                System.out.println("The read failed: " + databaseError.getCode());
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
//        // Attach a listener to read the data at our posts reference
//        tripDB.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Trip trip = dataSnapshot.getValue(Trip.class);
//                System.out.println(trip);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
    }
}
