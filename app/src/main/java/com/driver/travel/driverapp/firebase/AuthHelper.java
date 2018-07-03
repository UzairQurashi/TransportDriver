package com.driver.travel.driverapp.firebase;

import android.support.annotation.NonNull;

import com.driver.travel.driverapp.models.Driver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Uzair Qureshi on 6/28/2018.
 */

public class AuthHelper {
    public static void AuthLogin(String uid, final IFirebaseAuthListner<Driver> listner){

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        db.getReference(Reference.USER).child(Reference.DRIVER).child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot!=null&&dataSnapshot.exists()){
                    Driver driver=dataSnapshot.getValue(Driver.class);
                    listner.onSuccess(driver);

                }
                else {
                    listner.invalid("Invalid Creditionals");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listner.onFailure();

            }
        });


    }
}
