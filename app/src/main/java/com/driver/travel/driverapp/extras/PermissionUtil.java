package com.driver.travel.driverapp.extras;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


/**
 * Created by Uzair Qureshi on 2/15/2018.
 *
 * @purpose : This util is used to handle runtime permissions
 */

public class PermissionUtil {
    /**
     * this function will check the required permissions whether it is already allow or not.
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermission(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method is used to request
     *
     * @param requestcode
     * @param permission
     */
    public static void requestSpecificPermission(Activity activity, int requestcode, String... permission) {
        ActivityCompat.requestPermissions((activity), permission, requestcode);

    }

}
