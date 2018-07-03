package com.driver.travel.driverapp.models;

import java.util.List;

/**
 * Created by Uzair Qureshi on 7/3/2018.
 */

public class FcmResponse {

    private Integer multicastId;
    private Integer success;
    private Integer failure;
    private Integer canonicalIds;
    private List<Result> results = null;

    public Integer getMulticastId() {
        return multicastId;
    }

    public void setMulticastId(Integer multicastId) {
        this.multicastId = multicastId;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getCanonicalIds() {
        return canonicalIds;
    }

    public void setCanonicalIds(Integer canonicalIds) {
        this.canonicalIds = canonicalIds;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public class Result {

        public String messageId;

    }

}
