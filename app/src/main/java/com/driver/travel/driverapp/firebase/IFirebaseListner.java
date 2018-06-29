package com.driver.travel.driverapp.firebase;

/**
 * Created by Uzair Qureshi on 6/28/2018.
 */

public interface IFirebaseListner<T> {
    void onSuccess(T object);
    void invalid(String message);
    void onFailure();
}
