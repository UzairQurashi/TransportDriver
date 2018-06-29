package com.driver.travel.driverapp.firebase;

import com.driver.travel.driverapp.models.Driver;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Uzair Qureshi on 6/26/2018.
 */

public class QueryHelper {
    private static QueryHelper instance;
    private static FirebaseDatabase database;

    /**
     * singleton Constructor
     * @return
     */
    public static QueryHelper getInstance() {
        if (instance == null) {

            instance = new QueryHelper();
            database = FirebaseDatabase.getInstance();


        }
        return instance;
    }

    public void addDriver(Driver driver){
        database.getReference().child(Reference.USER).child(Reference.DRIVER).child(driver.getId()).setValue(driver);

    }

}
