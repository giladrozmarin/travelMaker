package com.example.travelmaker.travelmaker.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.travelmaker.travelmaker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeTraveler extends AppCompatActivity {


    FirebaseDatabase db;
    DatabaseReference db_ref;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private ArrayList<String> reg_trips = new ArrayList<>(), aval_trips = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;
//    private RecyclerView reg_view, aval_view;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_traveler);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        db = FirebaseDatabase.getInstance();
        db_ref = db.getReference("Trips");

        listView = (ListView) findViewById(R.id.available_trips);

//        aval_view = (RecyclerView) findViewById(R.id.available_trips);
//
//        mLayoutManager = new LinearLayoutManager(this);
//        aval_view.setLayoutManager(mLayoutManager);

//        mAdapter = new MA
        db_ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getKey();
                aval_trips.add(value);
                adapter = new ArrayAdapter<String>(HomeTraveler.this,android.R.layout.simple_list_item_1,aval_trips);
                listView.setAdapter(adapter);
            }
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String value = dataSnapshot.getKey();
//                aval_trips.add(value);
//                mAdapter = new RecyclerView.Adapter<String.RecyclerView.>(HomeTraveler.this,android.R.layout.simple_list_item_1,aval_trips);
//                aval_view.setAdapter(mAdapter);
//            }

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
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
