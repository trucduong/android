package com.example.trucduong.myseller;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.trucduong.myseller.dto.ComboboxItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by trucduong on 31/08/2016.
 */
public class MySpinnerHelper<K, T extends ComboboxItem<K, String>> {
    private List<T> values;
    private Spinner spinner;

    public MySpinnerHelper(Context context, Spinner spinner, List<T> values) {
        this.spinner = spinner;
        this.values = values;
        doCreateContent(context);
    }

    protected void doCreateContent(Context context) {
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (T value : values) {
            adapter.add(value.getValue());
        }
        spinner.setAdapter(adapter);
    }

    public void setSelectedValue(K value) {
        int position = indexOf(value);
        if (position != -1) {
            spinner.setSelection(position);
        }
    }

    public K getSelectedValue() {
        int position = spinner.getSelectedItemPosition();
        return values.get(position).getKey();
    }

    private int indexOf(K key) {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }
}
