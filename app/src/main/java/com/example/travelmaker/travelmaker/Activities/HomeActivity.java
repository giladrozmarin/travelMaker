package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelmaker.travelmaker.Models.Trip;
import com.example.travelmaker.travelmaker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    //Android layout
    private TextView hello;
    private ListView listView;
    private Button createTrip;
    //Firebase Var
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceUser;
    FirebaseDatabase firebaseDatabase;
    //Get curent user
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String UId = mAuth.getCurrentUser().getUid();
    //ArrayList
    ArrayList<String> array = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    //Strings
    String helloUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        //get my db
        firebaseDatabase = FirebaseDatabase.getInstance();
        //trips foucs
        databaseReference = firebaseDatabase.getReference("Trips");
        //user foucs
        databaseReferenceUser = firebaseDatabase.getReference("Users").child("TravelGuide").child(mAuth.getCurrentUser().getUid());
        //
        listView = (ListView) findViewById(R.id.database_list_view);

        //show hello user
        databaseReferenceUser.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                helloUser = dataSnapshot.getValue(String.class);
                hello.setText("hello "+helloUser);

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

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);


                    adapter = new ArrayAdapter<String>(HomeActivity.this, android.R.layout.simple_list_item_1, array);
                    listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //user hello
        hello = findViewById(R.id.usernameid);

        //creat trip btn
        createTrip = findViewById(R.id.createTrip);
        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTrip = new Intent(getApplicationContext(), createTrip.class);
                startActivity(createTrip);
                //finish();
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Trip tInfo = ds.getValue(Trip.class);
            if(UId.equals(tInfo.getGuide_name())) {
                array.add(tInfo.getCountry());
            }
        }

    }
    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
