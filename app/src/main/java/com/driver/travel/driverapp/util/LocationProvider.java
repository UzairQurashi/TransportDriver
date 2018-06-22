package com.driver.travel.driverapp.util;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Uzair Qureshi on 5/28/2018.
 * Description:this is centralised class for getting a cuurent location
 */
public class LocationProvider extends IntentService{
    private final static String TAG = "LocationProvider";
    private static LocationProvider instance;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    public LocationProvider() {
        super("LocationService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        buildFusedApiClient(getApplicationContext());
    }

    /**
     * this method will build a fused location api client
     *
     * @param context
     */
    @SuppressLint("MissingPermission")
    private synchronized void buildFusedApiClient(Context context) {
        Log.d(TAG, "Build fused api client");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);


    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mFusedLocationProviderClient!=null) {


            startLocationUpdates();
        }
        return START_STICKY;
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        Looper looper = Looper.myLooper();
        mFusedLocationProviderClient.requestLocationUpdates(getLocationRequest(), locationCallback, looper);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFusedLocationProviderClient != null) {
            mFusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }


    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return mFusedLocationProviderClient;

    }

    /**
     * this will return location request object
     * @return
     */
    private LocationRequest getLocationRequest() {
        return new LocationRequest()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(10 * 1000);

    }


    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult.getLastLocation()!=null) {
                Location lastLocation = locationResult.getLastLocation();
                Log.e("coordinates",""+lastLocation.getLatitude()+lastLocation.getLongitude());

               // setValue(lastLocation);
            }

        }
    };





    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
