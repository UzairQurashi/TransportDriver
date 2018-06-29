package com.driver.travel.driverapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.driver.travel.driverapp.databinding.ActivityMainScreenBinding;
import com.driver.travel.driverapp.delay.job.LocationSyncJob;
import com.driver.travel.driverapp.recyclerviews.ContactListAdapter;

public class MainScreen extends BaseActivity {
    ActivityMainScreenBinding binding;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        loadViews();

    }

    private void loadViews() {
        initrecyclerview();
        setEventListners();
    }

    private void setEventListners() {
       // binding.switchLocation.setOnCheckedChangeListener(new LocationSwitchListner());
    }
    /**
     * this method will initialize recyclerview
     *
     * @param
     */
    private void initrecyclerview() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.consumersList.setLayoutManager(mLayoutManager);
        binding.consumersList.setItemAnimator(new DefaultItemAnimator());
        initializeAdapter();


    }

    /**
     * this method will sets an recyclerview adapter
     *
     * @param
     */
    private void initializeAdapter() {
        adapter = new ContactListAdapter(this);
        binding.consumersList.setAdapter(adapter);

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
