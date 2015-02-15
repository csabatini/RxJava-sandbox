package com.csab.rxjava_sandbox;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csab.rxjava_sandbox.model.CurrencyResponse;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseFragment extends Fragment {

    private static final String TAG = "BaseFragment";
    private RxApplication mApp;
    private TextView mText;
    private Subscription mSub;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApp = (RxApplication) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mText = (TextView) view.findViewById(R.id.textView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSub = mApp.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(response -> response.getCurrencies())
                .flatMap(currencies -> Observable.from(currencies))
                .map(currency -> currency.getName())
                .take(5)
                .subscribe(name -> Log.d(TAG, name));

        // TODO: fill text view/list view with API response values instead
        mText.setText("Hello, world!");
    }
}
