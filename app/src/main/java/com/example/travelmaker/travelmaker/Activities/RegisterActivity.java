package com.example.travelmaker.travelmaker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.travelmaker.travelmaker.Models.User;
import com.example.travelmaker.travelmaker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends AppCompatActivity {


    private EditText userName, userEmail, userPassword, userPassword2;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;

    private Spinner sp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addItemsOnSpinner();
        addListenerOnSpinnerItemSelection();


        userName = findViewById(R.id.regName);
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        loadingProgress = findViewById(R.id.regProgressBar);
        regBtn = findViewById(R.id.regBtn);
        loadingProgress.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                regBtn.setVisibility((View.INVISIBLE));
                loadingProgress.setVisibility(View.VISIBLE);
                sp = (Spinner) findViewById(R.id.spinner);
                final String name = userName.getText().toString();
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String type = String.valueOf(sp.getSelectedItem());

                //something goes worng : all fields must be filled
                // we need to display an error message


                if (email.length() == 0|| name.length() == 0|| password .length() == 0||  !password.equals(password2)) {

                    showMassge("Please Verify all fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.VISIBLE);


                }
                else {

                    //everything is ok
                    //create user account

                    CreateUserAccount(email,name,password, type);
                }

            }
        });





    }

    private void CreateUserAccount(final String email, final String name, final String password, final String type) {

        // this method create user account
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //user account create successfully

                            showMassge("Account created");
                            // after we created user account we need to update his info
                            User user = new User (name,email,password,type);

                            FirebaseDatabase.getInstance().getReference("Users").child(user.type).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);

                            updateUserInfo(name,mAuth.getCurrentUser());
                            finish();
                            return;
                        }

                        else {
                            //account creation failed

                            showMassge("Account creation failed"+ task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);


                        }
                    }
                });


    }


    private void updateUserInfo(final String name, FirebaseUser currentUser) {

        final UserProfileChangeRequest profileupdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        updateUI();

        currentUser.updateProfile(profileupdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            showMassge("Register Complete");

                          //  showMassge("Register Complete");


                        }
                    }

                });



    }



    private void updateUI(){

        Intent homeActivity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMassge(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }

    public void addItemsOnSpinner() {

        sp = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Traveler");
        list.add("TravelGuide");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        sp = (Spinner) findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}
