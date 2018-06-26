package com.driver.travel.driverapp.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Uzair Qureshi on 4/11/2018.
 *
 * @purpose : this class is used to manage all local cache stuff
 */

public class PrefsManager {

    private static final String AUTH_TOKEN = "auth_token";
    private static final String DRIVER_KEY = "driver";

    private final SharedPreferences mPrefs;

    public PrefsManager(Context mContext) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public boolean setToken(String token) {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(AUTH_TOKEN, token);
        return prefsEditor.commit();
    }

    public String getAuthToken() {
        return mPrefs.getString(AUTH_TOKEN, "");
    }

//    public void saveDriver() {
//        SharedPreferences.Editor prefsEditor = mPrefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(Driver.getInstance());
//        prefsEditor.putString(DRIVER_KEY, json);
//        prefsEditor.commit();
//    }
//    public Driver getDriver() {
//        Gson gson = new Gson();
//        String json = mPrefs.getString(DRIVER_KEY, null);
//        Type type = new TypeToken<Driver>() {}.getType();
//        return gson.fromJson(json,type);
//    }
}
