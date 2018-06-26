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

import com.driver.travel.driverapp.databinding.ActivitySignUpScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpScreen extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SignUpScreen";
    FirebaseAuth mAuth;
    ActivitySignUpScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up_screen);
        loadViews();
    }

    /**
     * this method will load view for this screen
     */
    private void loadViews() {
        mAuth = FirebaseAuth.getInstance();
        setEventLIstners();
    }

    private void setEventLIstners() {
        binding.btnSignup.setOnClickListener(this);
        binding.linkLogin.setOnClickListener(this);
    }



    /**
     * this function will validates a user registration fields
     * @return
     */
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
        String confirm_password=binding.inputConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(confirm_password)) {
            binding.inputConfirmPassword.setError("Required.");
            valid = false;
        } else {
            binding.inputConfirmPassword.setError(null);
        }
        if(!password.equals(confirm_password)){
            Toast.makeText(this,"Mismatch Password",Toast.LENGTH_SHORT).show();
            valid=false;
        }


        return valid;
    }


    /**
     *  this method will create a new user after successful validation
     * @param email
     * @param password
     */
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpScreen.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        //updateUI(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                createAccount(binding.inputEmail.getText().toString(),binding.inputPassword.getText().toString());

                break;

            case R.id.link_login:
                openLoginActivity();
            default:
        }
    }

    private void openLoginActivity() {
        Intent intent=new Intent(this,LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
