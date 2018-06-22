package com.driver.travel.driverapp.delay.job;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.driver.travel.driverapp.util.LocationProvider;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by Uzair Qureshi on 6/21/2018.
 */

public class LocationSyncJob extends Job {
    public static final String TAG = "location_job_tag";
    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        // run location job here
        //Log.e(TAG,"Locatoin trigger by scheduling");
        startService(getContext());

        return Result.SUCCESS;
    }
    private void startService(Context context)
    {
        context.startService(new Intent(context, LocationProvider.class));


    }
    public static void scheduleJob() {
        new JobRequest.Builder(TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(2))
                .setUpdateCurrent(true)//this request will cancel any preexisting job with the same tag while being scheduled.?true :false

                .build()
                .schedule();
    }
}
