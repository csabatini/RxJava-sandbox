package com.csab.rxjava_sandbox.data;

import android.util.Log;

import com.csab.rxjava_sandbox.AppConfig;
import com.csab.rxjava_sandbox.model.Currency;
import com.csab.rxjava_sandbox.network.Api;

import java.util.List;

import au.com.gridstone.grex.GRexPersister;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Repository {

    private final String TAG = getClass().getSimpleName();
    private GRexPersister mPersister;
    private Api mApi;

    Observable<List<Currency>> mCurrencyStream = Observable.empty();

    // TODO: add composite subscription in order to unsub/resub with android lifecycles
    // TODO: add unit tests for this class
    public Repository(GRexPersister persister, Api api) {
        this.mPersister = persister;
        this.mApi = api;
    }

    public Observable<List<Currency>> getCurrencies() {
        // Make retrofit API request
        mCurrencyStream = mApi.currencies()
            .map(response -> response.getCurrencies());

        // Subscribe to API observable, writing results to disk
        mCurrencyStream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(list -> saveToDisk(list));

        // Return observable front-loaded with cached results (if any)
        return mCurrencyStream.startWith(
            mPersister.getList(AppConfig.CURRENCY_KEY, Currency.class));
    }

    // TODO: generalize method to save multiple models
    private void saveToDisk(List<Currency> list) {
        Observable<List<Currency>> result =
            mPersister.putList(AppConfig.CURRENCY_KEY, list, Currency.class);

        result.subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<List<Currency>>() {
                  @Override
                  public void onCompleted() {
                      Log.d(TAG, "saveToDisk onComplete!");
                  }

                  @Override
                  public void onError(Throwable e) {
                      Log.d(TAG, "" + e.getMessage());
                  }

                  @Override
                  public void onNext(List<Currency> currencies) {
                      Log.d(TAG, "saveToDisk onNext - size " + currencies.size());
                  }
              });
    }

}