package com.example.travelmaker.travelmaker.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelmaker.travelmaker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeTraveler extends AppCompatActivity {

    //FireBase var
      //Get my db
    FirebaseDatabase db;
    DatabaseReference db_ref;
    DatabaseReference databaseReferenceUser;
    DatabaseReference databaseReferenceId;
       //Get curent user
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String UId = mAuth.getCurrentUser().getUid();

    //Android layout
    private TextView hello;
    private ListView listViewAvailableTrip;
    private ListView listViewMyTrip;
    //ArrayList
    private ArrayList<String> reg_trips = new ArrayList<>(), aval_trips = new ArrayList<>();
    private ArrayAdapter<String> adapter,adapter1;
    //Strings
    String helloUser;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_traveler);

            //get my db
            db = FirebaseDatabase.getInstance();
            //trip foucs
            db_ref = db.getReference("Trips");
            //user foucs
            databaseReferenceUser = db.getReference("Users").child("Traveler").child(mAuth.getCurrentUser().getUid());
            //trip user id
            databaseReferenceId=  db.getInstance().getReference("travelers in tripid");


            //connect java to text view
            hello = findViewById(R.id.usernameid3);
            //show hello user
            databaseReferenceUser.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    helloUser = dataSnapshot.getValue(String.class);
                    hello.setText("  Hello "+helloUser);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //connect java to lists view
            listViewMyTrip = (ListView) findViewById(R.id.my_trips);
            listViewAvailableTrip = (ListView) findViewById(R.id.available_trips);
            //trips available
            db_ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                    String value = dataSnapshot.getKey();
                    aval_trips.add(value);



                  //  available trips
                    adapter1 = new ArrayAdapter<String>(HomeTraveler.this,android.R.layout.simple_list_item_1,aval_trips);
                    listViewAvailableTrip.setAdapter(adapter1);

                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            //trips reg
            databaseReferenceId.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    showData(dataSnapshot);
                    //my trips
                       adapter = new ArrayAdapter<String>(HomeTraveler.this,android.R.layout.simple_list_item_1,reg_trips);
                       listViewMyTrip.setAdapter(adapter);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    private void showData(DataSnapshot dataSnapshot) {



        for (DataSnapshot ds : dataSnapshot.getChildren()) {




            showMassge(ds.getValue().toString());



            if(UId.equals( ds.getValue().toString()))
            {

                String trip = ds.getRef().getParent().toString();
                String trip1 = trip.substring(trip.lastIndexOf('/'));
                trip=trip1.substring(1, trip1.length());


                reg_trips.add(trip);

            }

        }

    }

    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
    }

