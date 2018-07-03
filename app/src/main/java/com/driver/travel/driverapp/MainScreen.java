package com.driver.travel.driverapp;

import android.*;
import android.Manifest;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.driver.travel.driverapp.databinding.ActivityMainScreenBinding;
import com.driver.travel.driverapp.delay.job.LocationSyncJob;
import com.driver.travel.driverapp.extras.Constants;
import com.driver.travel.driverapp.extras.PermissionUtil;
import com.driver.travel.driverapp.firebase.ConsumerHelper;
import com.driver.travel.driverapp.firebase.IFireBaseDBListner;
import com.driver.travel.driverapp.models.Consumer;
import com.driver.travel.driverapp.recyclerviews.ContactListAdapter;

import java.util.ArrayList;


public class MainScreen extends BaseActivity {
    ActivityMainScreenBinding binding;
    private ContactListAdapter adapter;
    private Switch switchAB;

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
        getAllConsumers("");


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
        getMenuInflater().inflate(R.menu.menu_main, menu);

        switchAB = (Switch)menu.findItem(R.id.switchId)
                .getActionView().findViewById(R.id.switchAB);

        switchAB.setOnCheckedChangeListener(new LocationSwitchListner());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void getAllConsumers(String driverid) {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (PermissionUtil.hasPermission(this, perms)) {
            {
                ConsumerHelper.getAllConsumer(driverid, new IFireBaseDBListner<ArrayList<Consumer>>() {
                    @Override
                    public void onSuccess(ArrayList<Consumer> consumerlist) {
                        adapter.refreshData(consumerlist);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        } else {
            PermissionUtil.requestSpecificPermission(this, Constants.CALL_PERM_REQUEST, perms);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.CALL_PERM_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    // initAdapter();
                    getAllConsumers("");
                }
                break;

                case Constants.LOCATION_PERM_REQUEST:
                    if (grantResults.length > 0 && grantResults[0] == 0) {
                        LocationSyncJob.schedulePeriodicJob();

                    }
                    break;

        }

    }

    private class LocationSwitchListner implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton.isChecked() == true) {

                String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
                if (PermissionUtil.hasPermission(MainScreen.this, perms)) {
                    LocationSyncJob.schedulePeriodicJob();
                }
                else {
                    PermissionUtil.requestSpecificPermission(MainScreen.this, Constants.LOCATION_PERM_REQUEST, perms);

                }


            } else {
                LocationSyncJob.cancleJob();
            }
        }
    }

}
