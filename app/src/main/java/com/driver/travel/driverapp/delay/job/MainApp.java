package com.driver.travel.driverapp.delay.job;

import android.app.Application;

import com.evernote.android.job.JobConfig;
import com.evernote.android.job.JobManager;

import static com.evernote.android.job.JobConfig.setAllowSmallerIntervalsForMarshmallow;

/**
 * Created by Uzair Qureshi on 6/21/2018.
 */

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new IJobCreator());

        JobConfig.setAllowSmallerIntervalsForMarshmallow(true); // Don't use this in production

    }

}
