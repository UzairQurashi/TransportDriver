package com.driver.travel.driverapp.retrofit;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Uzair Qureshi on 4/11/2018.
 */

public class RestClient {

    private static WebCalls authTokenClient;

    static {
        setupClient();
    }

    private static void setupClient() {

        // Todo remove log level while release


    }

    public static WebCalls getRestAdapter() {

        /*Un comment the below line when we want to Log our netwrk calls**/
       HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request newRequest;
                newRequest = request.newBuilder()
                        .addHeader(NetworkConstants.AUTHORIZATION,  "key="+NetworkConstants.SERVER_KEY)
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(newRequest);
            }
        })
                .connectTimeout(NetworkConstants.TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(NetworkConstants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                client(httpClient).
                build();

     return    authTokenClient = retrofit.create(WebCalls.class);
    }




}
