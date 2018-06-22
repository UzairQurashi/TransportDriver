package com.driver.travel.driverapp.delay.job;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.driver.travel.driverapp.extras.Constants;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by Uzair Qureshi on 6/21/2018.
 */

class IJobCreator implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        switch (tag) {
            case LocationSyncJob.TAG:
                return new LocationSyncJob();
            default:
                return null;

        }

    }
}
