package com.driver.travel.driverapp.recyclerviews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.driver.travel.driverapp.databinding.ContactItemListBinding;
import com.driver.travel.driverapp.databinding.ContactListBinding;
import com.driver.travel.driverapp.models.Consumer;
import com.driver.travel.driverapp.models.FcmRequest;
import com.driver.travel.driverapp.models.FcmResponse;
import com.driver.travel.driverapp.retrofit.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Uzair Qureshi on 6/29/2018.
 */

class ContactVH extends RecyclerView.ViewHolder {
    private ContactItemListBinding binding;

    public ContactVH( ContactItemListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }



    public void bindViews(final Consumer consumer) {
        binding.consumerEmail.setText(""+consumer.getEmail());
        binding.consumerName.setText(""+consumer.getName());

        binding.notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification(consumer.getFcm_token());
            }
        });
        binding.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callConsumer(consumer.getPhoneNo());
            }
        });


    }

    @SuppressLint("MissingPermission")
    private void callConsumer(String phoneNo){
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + phoneNo));
        binding.getRoot().getContext().startActivity(intent);

    }

    /**
     * this method is send to push notification to seleted consumer
     * @param userToken
     */
    private void sendNotification(String userToken){
        final FcmRequest request=new FcmRequest();
        request.setTo(userToken);
        FcmRequest.Data data=new FcmRequest().new Data();
        data.setMessage("Mohidudin");
        data.setTittle("Driver has arrived");
        request.setData(data);
        RestClient.getRestAdapter().sendNotification(request).enqueue(new Callback<FcmResponse>() {
            @Override
            public void onResponse(Call<FcmResponse> call, Response<FcmResponse> response) {
                if(response.isSuccessful())
                Snackbar.make(binding.getRoot(), "Send Notification", Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<FcmResponse> call, Throwable t) {
                Snackbar.make(binding.getRoot(), "Some Error Found", Snackbar.LENGTH_LONG).show();

            }
        });


    }
}
