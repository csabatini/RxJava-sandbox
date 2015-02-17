package com.csab.rxjava_sandbox.network;

import com.csab.rxjava_sandbox.model.CurrencyResponse;

import retrofit.http.GET;
import rx.Observable;

public interface Api {

    @GET("/currencies")
    Observable<CurrencyResponse> currencies();
}
