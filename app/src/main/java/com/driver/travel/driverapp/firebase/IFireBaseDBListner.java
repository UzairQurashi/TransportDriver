package com.driver.travel.driverapp.firebase;

/**
 * Created by Uzair Qureshi on 7/2/2018.
 */

public interface IFireBaseDBListner<T> {
    public void onSuccess(T object);
    public void onFailure();

}
