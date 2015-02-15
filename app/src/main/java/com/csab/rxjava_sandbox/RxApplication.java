package com.csab.rxjava_sandbox;

import android.app.Application;

import com.csab.rxjava_sandbox.model.CurrencyResponse;
import com.csab.rxjava_sandbox.network.Api;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;

public class RxApplication extends Application {

    private static Api mApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiService = buildApi();
    }

    private Api buildApi() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Api.API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        return adapter.create(Api.class);
    }

    private Observable<CurrencyResponse> getData() {
        return mApiService.data();
    }

}
