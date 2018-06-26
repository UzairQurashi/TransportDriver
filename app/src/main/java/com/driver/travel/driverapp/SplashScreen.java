package com.driver.travel.driverapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends BaseActivity {

    private static final long SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        openNewScreen();
    }
    /**
     * this method will open login screen after short delay
     */
    private void openNewScreen() {
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                Intent i;
                if (isUserLogin()) {
                    i = new Intent(SplashScreen.this, MainScreen.class);

                } else {

                    i = new Intent(SplashScreen.this, LoginScreen.class);


                }
                startActivity(i);
                finish();

            }
        }, SPLASH_TIME_OUT);


    }

    private boolean isUserLogin() {
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            return true;
        }
        return false;
    }
}
