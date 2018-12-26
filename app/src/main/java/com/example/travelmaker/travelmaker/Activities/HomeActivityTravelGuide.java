package com.example.travelmaker.travelmaker.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.travelmaker.travelmaker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivityTravelGuide extends AppCompatActivity {

    public  String myUser ;
    private FirebaseAuth mAuth;
    private FirebaseAuth firebaseAuth;
    FirebaseUser user = firebaseAuth.getCurrentUser();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_travel_guide);






        TextView ShowmyUser = (TextView) findViewById(R.id.usernameid);
        String hello = "Hi";

        if(user != null) {

            hello = hello.concat(" ").concat(user.getDisplayName());
        }
        else{
            hello = "User not logged in";
        }



    }
}
