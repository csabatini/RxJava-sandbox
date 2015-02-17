package com.csab.rxjava_sandbox.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csab.rxjava_sandbox.R;
import com.csab.rxjava_sandbox.RxApplication;
import com.csab.rxjava_sandbox.adapter.CurrencyAdapter;
import com.csab.rxjava_sandbox.model.Currency;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

// TODO: add unit tests for this class
public class BaseFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private CompositeSubscription mSub = new CompositeSubscription();
    private RxApplication mApp;
    private CurrencyAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mApp = (RxApplication) getActivity().getApplication();
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        // TODO: convert to RecyclerView
        ListView listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new CurrencyAdapter(getActivity());
        listView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSub.add(
            mApp.getRepository().getCurrencies()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<List<Currency>>() {
                        @Override
                        public void onCompleted() {
                            mAdapter.notifyDataSetChanged();
                            Log.d(TAG, "onComplete!");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, e.getMessage() + "");
                        }

                        @Override
                        public void onNext(List<Currency> currencies) {
                            mAdapter.clear();
                            mAdapter.addAll(currencies);
                            Log.d(TAG, "Resetting adapter - input size " + currencies.size());
                        }
                    }));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSub.clear();
    }
}
