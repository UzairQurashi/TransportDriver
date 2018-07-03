package com.driver.travel.driverapp.retrofit;


import com.driver.travel.driverapp.models.FcmRequest;
import com.driver.travel.driverapp.models.FcmResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Uzair Qureshi on 4/11/2018.
 * Description:All network callbacks
 */

public interface WebCalls {

    @POST(EndPoints.FCM_SEND)
    Call<FcmResponse> sendNotification(@Body FcmRequest fcmRequest);

}
