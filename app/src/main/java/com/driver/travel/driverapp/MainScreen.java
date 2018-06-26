package com.driver.travel.driverapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.driver.travel.driverapp.databinding.ActivityMainScreenBinding;
import com.driver.travel.driverapp.delay.job.LocationSyncJob;

public class MainScreen extends BaseActivity {
    ActivityMainScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        loadViews();

    }

    private void loadViews() {
        setEventListners();
    }

    private void setEventListners() {
        binding.switchLocation.setOnCheckedChangeListener(new LocationSwitchListner());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class LocationSwitchListner implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton.isChecked() == true) {
                LocationSyncJob.schedulePeriodicJob();
            }
            else {
                LocationSyncJob.cancleJob();
            }
        }
    }

}
