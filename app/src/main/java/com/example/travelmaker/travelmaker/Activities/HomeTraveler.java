package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button logoutbt;
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
            //trips available
            db_ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                    String value = dataSnapshot.getKey();


                    String[] arrSt = value.split(" ");
                    String trip = value.substring(arrSt[0].length() + 1, value.length());



                    aval_trips.add(trip);



                    //  available trips
                    aval_trips.removeAll(reg_trips);


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


            listViewAvailableTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent regTrip = new Intent(getApplicationContext(), regTrip.class);
                    regTrip.putExtra("Trip",adapter1.getItem(i));
                    startActivity(regTrip);
                }
            });
            logoutbt = findViewById(R.id.logoutbt);

            logoutbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateUI();

                }
            });


        }

    private void showData(DataSnapshot dataSnapshot) {



        for (DataSnapshot ds : dataSnapshot.getChildren()) {




            if(ds.hasChild(UId))
            {
                String trip = ds.getRef().getParent().toString();
                String trip1 = trip.substring(trip.lastIndexOf('/'));
                trip1 =trip1.replace("%20"," ");
                String[] arrSt = trip1.split(" ");
                String trip_name = trip1.substring(arrSt[0].length()+1, trip1.length());



                reg_trips.add(trip_name);

            }



        }

    }

    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
    private void updateUI() {
        FirebaseAuth.getInstance().signOut();
        Intent LoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(LoginActivity);
        finish();
    }
    }

