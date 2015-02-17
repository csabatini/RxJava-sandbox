package com.csab.rxjava_sandbox;

import android.util.Log;

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

    public Repository(GRexPersister persister, Api api) {
        this.mPersister = persister;
        this.mApi = api;
    }

    public Observable<List<Currency>> getData() {
        Observable<List<Currency>> observable = mApi.data()
            .map(response -> response.getCurrencies())
            .startWith(mPersister.getList("currency", Currency.class))
            .distinct();

        // TODO: subscribing to observable here makes onNext not trigger in fragment?
        //observable.subscribe(list -> saveToDisk(list));

        return observable;
    }

    private void saveToDisk(List<Currency> list) {
        Observable<List<Currency>> result = mPersister.putList("currency", list, Currency.class);
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
                      Log.d(TAG, "saveToDisk onNext!");
                  }
              });
    }

}