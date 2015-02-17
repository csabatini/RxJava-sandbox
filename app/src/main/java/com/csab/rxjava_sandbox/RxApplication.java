package com.csab.rxjava_sandbox;

import android.app.Application;

import com.csab.rxjava_sandbox.network.Api;
import com.squareup.okhttp.OkHttpClient;

import au.com.gridstone.grex.GRexPersister;
import au.com.gridstone.grex.converters.GsonConverter;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RxApplication extends Application {

    private static Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        mRepository = new Repository(buildCache(), buildApi());
    }

    public Repository getRepository() {
        return mRepository;
    }

    private Api buildApi() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(Api.API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();
        return adapter.create(Api.class);
    }

    private GRexPersister buildCache() {
        return new GRexPersister(this, "cache", new GsonConverter());
    }

}
