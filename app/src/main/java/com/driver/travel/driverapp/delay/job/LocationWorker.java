package com.driver.travel.driverapp.delay.job;

import android.support.annotation.NonNull;

import androidx.work.Worker;

/**
 * Created by Uzair Qureshi on 6/28/2018.
 */

public class LocationWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {

        //do the work you want done on the background in here

        return Result.SUCCESS;
    }
}
