package com.driver.travel.driverapp.models;

/**
 * Created by Uzair Qureshi on 7/3/2018.
 */

public class FcmRequest {
    public String to;
    public Data data;


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



    public class Data{
        private String message;
        private String tittle;
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTittle() {
            return tittle;
        }

        public void setTittle(String tittle) {
            this.tittle = tittle;
        }




    }
}
