package com.driver.travel.driverapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.driver.travel.driverapp.databinding.ActivityLoginScreenBinding;
import com.driver.travel.driverapp.delay.job.LocationSyncJob;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        loadViews();
    }

    private void loadViews() {
        setClickListners();
    }

    private void setClickListners() {
        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                LocationSyncJob.scheduleJob();
                default:

        }
    }
}
