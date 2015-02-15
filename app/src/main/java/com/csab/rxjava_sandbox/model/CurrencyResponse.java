package com.csab.rxjava_sandbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrencyResponse {

    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Currency> currencies = new ArrayList<>();

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The currencies
     */
    public List<Currency> getCurrencies() {
        return currencies;
    }

    /**
     *
     * @param data
     * The currencies
     */
    public void setCurrencies(List<Currency> data) {
        this.currencies = data;
    }

}