package com.example.chatterbox.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatterbox.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {


    private static final String TAG = "MainActivityLog";
    public EditText phoneNo,otp;
    public TextView sendotp,submitotp;
    public ProgressBar progressBar;

    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public String mVerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phoneNo = findViewById(R.id.enter_phoneNo);
        otp = findViewById(R.id.enter_otp);
        sendotp = findViewById(R.id.send_otp);
        submitotp = findViewById(R.id.submit_otp);
        progressBar = findViewById(R.id.progressbar);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(phoneNo.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "Requesting OTP");

            }
        });
    }


    public void sendOTP(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {

                        String CODE = credential.getSmsCode();
                        if(CODE != null){
                            Log.d(TAG, "OTP verified automatically (same device)" + credential);
                            otp.setText(CODE);
                            progressBar.setVisibility(View.VISIBLE);
                            verifycode(CODE);
                        }
                        Log.d(TAG, "onVerificationCompleted:" + credential);

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        otp.setText("");
                        progressBar.setVisibility(View.INVISIBLE);
                        Log.w(TAG, "onVerificationFailed", e);  // Incorrect phone no !!!!

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(MainActivity.this, "Incorrect format of phone no.", Toast.LENGTH_SHORT).show();
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // The SMS quota for the project has been exceeded
                        }

                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {

                        Log.d(TAG, "onCodeSent:" + verificationId);

                        sendotp.setVisibility(View.INVISIBLE);
                        phoneNo.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);

                        submitotp.setVisibility(View.VISIBLE);
                        otp.setVisibility(View.VISIBLE);

                        // Save verification ID and resending token so we can use them later
                        mVerificationId = verificationId;
                        getcode();


                    }
                });

    }

    private void verifycode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }


    private void getcode() {
        submitotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String USER_TYPED_CODE = otp.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "OTP Submitted", Toast.LENGTH_SHORT).show();
                verifycode(USER_TYPED_CODE);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success...
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            Boolean checkNewUser = task.getResult().getAdditionalUserInfo().isNewUser();

                            if (checkNewUser == true) {
                                // Get User details and bio...
                                CreateFirebaseDatabase(user);
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                intent.putExtra("PhoneNo", user.getPhoneNumber());
                                intent.putExtra("PhoneNo", user.getPhoneNumber());
                                intent.putExtra("NewUser", "true");
                                intent.putExtra("Editable", "true");
                                startActivity(intent);
                            } else {
                                // Goto Homepage...
                                Intent intent = new Intent(MainActivity.this, HomePage.class);
                                startActivity(intent);
                            }

                            Toast.makeText(MainActivity.this, "Signed in using" + user.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                        } else {
                            // Sign in failed...
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(MainActivity.this, "Signed in failed: Invalid verification code", Toast.LENGTH_SHORT).show();
                            }
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }


    private void CreateFirebaseDatabase(FirebaseUser user) {

        FirebaseDatabase mfirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mdatabaseReference = mfirebaseDatabase.getReference().child("User Data");
        mdatabaseReference.child(user.getPhoneNumber()).child("UID").setValue(user.getUid());

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart : ");

        // Check if user is signed in (non-null)

        if(mAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Toast.makeText(MainActivity.this, currentUser.getDisplayName() + " Already logged in", Toast.LENGTH_LONG).show();
            Log.d(TAG,currentUser.toString());
            Intent intent = new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this, "Please sign in...", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}




