package com.csab.rxjava_sandbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

// TODO: add robolectric unit tests for this class
public class BaseFragment extends Fragment {

    private CompositeSubscription mSub = new CompositeSubscription();
    private RxApplication mApp;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mApp = (RxApplication) getActivity().getApplication();
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSub.add(
            mApp.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(response -> response.getCurrencies())
                .flatMap(currencies -> Observable.from(currencies))
                .map(currency -> currency.getName())
                .take(5)
                .subscribe(name -> updateAdapter(name)));

    }

    private void updateAdapter(String name) {
        // TODO: in addition to add, account for 'update'
        if (mAdapter.getPosition(name) == -1) {
            mAdapter.add(name);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSub.unsubscribe();
    }
}
