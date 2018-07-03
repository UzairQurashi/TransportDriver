package com.driver.travel.driverapp.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.driver.travel.driverapp.models.Consumer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Uzair Qureshi on 7/2/2018.
 * Description: This help[er class will get all consumers of specific driver
 */

public class ConsumerHelper {
    public static void getAllConsumer(String driverid, final IFireBaseDBListner<ArrayList<Consumer>> listner) {


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference(Reference.USER).child(Reference.CONSUMERS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    ArrayList<Consumer> consumers = new ArrayList<>();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Consumer consumer = postSnapshot.getValue(Consumer.class);
                        consumers.add(consumer);
                    }
                    listner.onSuccess(consumers);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listner.onFailure();

            }
        });


    }
}
