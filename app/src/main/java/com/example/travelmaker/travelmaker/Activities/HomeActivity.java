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
import android.widget.Toast;

import com.example.travelmaker.travelmaker.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

   //Android layout
    //private DatabaseReference tripDB= FirebaseDatabase.getInstance().getReference("Trips");
    private ListView listView ;
    private Button createTrip;



    //Firebase Var
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    //ArrayList
    private ArrayList<String> arrayList = new  ArrayList<>();
    private ArrayAdapter<String>  adapter;






    //FirebaseAuth mAuth = FirebaseAuth.getInstance();

   // DatabaseReference db=FirebaseDatabase.getInstance().getReference();
  //  DatabaseReference users = db.child("Users").child(mAuth.getCurrentUser().getUid()).child("userName") ;

     //public String UID = user.getUid();


        //child(user.type).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
        // = db.child("Users").child(mAuth.getCurrentUser().getUid()).child("userName")
    //final TextView ShowmyUser = (TextView) findViewById(R.id.usernameid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Trips");
        listView = (ListView) findViewById(R.id.database_list_view);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getKey();
                arrayList.add(value);
                adapter = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(adapter);
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


        createTrip = findViewById(R.id.createTrip);
        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTrip = new Intent(getApplicationContext(),createTrip.class);
                startActivity(createTrip);
//                finish();;
            }
        });

//

    }


    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }

}
