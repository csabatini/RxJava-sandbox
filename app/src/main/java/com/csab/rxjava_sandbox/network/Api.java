package com.csab.rxjava_sandbox.network;

import com.csab.rxjava_sandbox.model.CurrencyResponse;

import retrofit.http.GET;
import rx.Observable;

public interface Api {

    public static final String API_URL = "https://www.cryptsy.com/api/v2";

    @GET("/currencies")
    Observable<CurrencyResponse> data();
}
