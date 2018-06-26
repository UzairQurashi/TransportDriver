package com.driver.travel.driverapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.driver.travel.driverapp.databinding.ActivityLoginScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginScreen";
    ActivityLoginScreenBinding binding;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        loadViews();
    }

    private void loadViews() {
        mAuth=FirebaseAuth.getInstance();
        setClickListners();
    }

    private void setClickListners() {
        binding.btnLogin.setOnClickListener(this);
        binding.linkSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //  LocationSyncJob.schedulePeriodicJob();
                driversignIn(binding.inputEmail.getText().toString(),binding.inputPassword.getText().toString());
                break;
            case R.id.link_signup:
                openActivityWithFinish(SignUpScreen.class);
                break;
            default:

        }
    }



    private void driversignIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginScreen.this, "Successful login." +""+user.getUid(),
                                    Toast.LENGTH_SHORT).show();
                            openActivityWithFinish(MainScreen.class);
                            //here we call location service

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                          //  mStatusTextView.setText(R.string.auth_failed);
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = binding.inputEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            binding.inputEmail.setError("Required.");
            valid = false;
        } else {
            binding.inputEmail.setError(null);
        }

        String password = binding.inputPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            binding.inputPassword.setError("Required.");
            valid = false;
        } else {
            binding.inputPassword.setError(null);
        }

        return valid;
    }


}
