package com.csab.rxjava_sandbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.csab.rxjava_sandbox.model.Currency;

public class CurrencyAdapter extends ArrayAdapter<Currency> {

    LayoutInflater mInflater;

    public CurrencyAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }

        Currency cd = getItem(position);
        ((TextView) view.findViewById(android.R.id.text1)).setText(cd.getName());
        return view;
    }
}