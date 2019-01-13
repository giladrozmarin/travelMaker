package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.travelmaker.travelmaker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {


    private EditText userMail,userPassword;
    private Button btnLogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity, HomeTraveler;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.login_mail);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.loginBtn);
        loginProgress = findViewById(R.id.login_progress);
        mAuth = FirebaseAuth.getInstance();
        HomeTraveler = new Intent(this,com.example.travelmaker.travelmaker.Activities.HomeTraveler.class);
        HomeActivity = new Intent(this,com.example.travelmaker.travelmaker.Activities.HomeActivity.class);
        signUp = findViewById(R.id.sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                // TODO add startActivityForResult() - to get back login credentials after user signs up and login with it
//                finish();;
            }
        });
        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginProgress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Field");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    signIn(mail,password);
                }
            }
        });
        //DB read test

    }

        private void signIn(final String mail, String password) {


            mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful()) {


                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference uidRef = rootRef.child("Users").child("Traveler").child(uid);


                        ValueEventListener valueEventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    //start traveler activity
                                    HomeTraveler.setAction(Intent.ACTION_SEND);
                                    HomeTraveler.setType("text/plain");
                                    HomeTraveler.putExtra(Intent.EXTRA_TEXT, mail);
                                    loginProgress.setVisibility(View.INVISIBLE);
                                    btnLogin.setVisibility(View.VISIBLE);
                                    startActivity(HomeTraveler);
                                    finish();

                                } else {
                                    //start travel guide activity
                                    HomeActivity.setAction(Intent.ACTION_SEND);
                                    HomeActivity.setType("text/plain");
                                    HomeActivity.putExtra(Intent.EXTRA_TEXT, mail);
                                    loginProgress.setVisibility(View.INVISIBLE);
                                    btnLogin.setVisibility(View.VISIBLE);
                                    startActivity(HomeActivity);;
                                    finish();
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError)
                            {
                            }
                        };
                        uidRef.addListenerForSingleValueEvent(valueEventListener);




                    }
                else {
                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }


            }
        });



    }

    private void updateUI() {

        startActivity(HomeActivity);
       // startActivity(HomeTraveler);
        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("Users").child("Traveler").child(uid);

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        startActivity(HomeTraveler);
                        finish();

                    } else {

                        startActivity(HomeActivity);;
                        finish();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError)
                {
                }
            };
            uidRef.addListenerForSingleValueEvent(valueEventListener);



        }



    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume", "The onResume() event");
    }

}