package com.driver.travel.driverapp.extras;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Uzair Qureshi on 7/2/2018.
 */

public class PermissionHelper {
    public static void requestPErmission(Context context,String permission,int requestcode ){
        if (ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.WRITE_CALENDAR}, requestcode);



        }}

}
