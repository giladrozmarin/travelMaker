package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelmaker.travelmaker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regTrip extends AppCompatActivity {


    //FireBase var
    //Get my db
    FirebaseDatabase db;
    DatabaseReference db_ref;
    DatabaseReference databaseReferenceId;
    //Android layout
    private TextView tripName;
    private Button regTrip_btn;
    private ProgressBar loadingProgress;
    private FloatingActionButton fab;
    private FloatingActionButton floatingActionButton12;
    //Get curent user
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String UId = mAuth.getCurrentUser().getUid();
    //
    String b,c;
    String guide;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_trip);

        //get my db
        db = FirebaseDatabase.getInstance();
        //trip foucs
        db_ref = db.getReference("Trips");
        //trip user id
        databaseReferenceId =  db.getReference("travelers in tripid");

        data = getIntent().getExtras().getString("Trip","defaultKey");

        tripName = findViewById(R.id.textView18);
        regTrip_btn = findViewById(R.id.createTrip2);
        loadingProgress = findViewById(R.id.regProgressBar2);
        fab =  findViewById(R.id.floatingActionButton13);
        floatingActionButton12 = findViewById(R.id.floatingActionButton12);

        loadingProgress.setVisibility(View.INVISIBLE);

        //Trip Name
        tripName.setText(" Trip Name: "+data);
        //trip reg
        db_ref.addChildEventListener(new ChildEventListener() {
                                         @Override
                                         public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                             showData(dataSnapshot,data);

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





      fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI1();
            }
        });

      floatingActionButton12.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              showMassge("The option will be available in the next version");

          }
      });


                regTrip_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        databaseReferenceId.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                showData1(dataSnapshot);


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

                        //invisible btn
                        regTrip_btn.setVisibility((View.INVISIBLE));
                        //visible btn
                        loadingProgress.setVisibility(View.VISIBLE);
                        showMassge("Registration successful!");
                        updateUI();

                    }

                });

    }
    private  void  showData1(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            String trip = ds.getRef().getParent().toString();
            String trip1 = trip.substring(trip.lastIndexOf('/'));
            trip1 =trip1.replace("%20"," ");
            String[] arrSt = trip1.split(" ");
            String b = arrSt[0];
            String trip_name = trip1.substring(arrSt[0].length()+1, trip1.length());
            b=b.substring(1,b.length());

            if(guide.equals(b+" "+trip_name))
            {

                FirebaseDatabase.getInstance().getReference("travelers in tripid").child(guide).child(trip_name).child(UId).setValue("");;
            }

        }

    }
    private void showData(DataSnapshot dataSnapshot,String data) {

        for (DataSnapshot ds : dataSnapshot.getChildren()) {



                String trip = ds.getRef().getParent().toString();
                String trip1 = trip.substring(trip.lastIndexOf('/'));
                trip1 =trip1.replace("%20"," ");
                String[] arrSt = trip1.split(" ");
                b = arrSt[0];
                String trip_name = trip1.substring(arrSt[0].length()+1, trip1.length());
                b=b.substring(1,b.length());

            if(data.equals(trip_name))
                {
                   c=b;
                   guide = b+" "+trip_name;

                }

                }
                }

    private void updateUI() {

        Intent homeTraveler = new Intent(getApplicationContext(), HomeTraveler.class);
        startActivity(homeTraveler);
        finish();
    }
    private void updateUI1() {

        Intent tripInfo = new Intent(getApplicationContext(), tripInfo.class);
        tripInfo.putExtra("Trip",data);
        tripInfo.putExtra("guide",c);
        startActivity(tripInfo);
        finish();
    }

    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
}
