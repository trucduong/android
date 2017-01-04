package com.example.trucduong.myseller;

import android.content.Intent;
import android.widget.EditText;

/**
 * Created by trucduong on 29/08/2016.
 */
public class ValueUtils {

    public static int getInt(EditText edt, int defaultVal) {
        if (isEmpty(edt)) {
            return  defaultVal;
        }

        return Integer.parseInt(edt.getText().toString());
    }

    public static String getString(EditText edt, String defaultVal) {
        if (isEmpty(edt)) {
            return  defaultVal;
        }

        return edt.getText().toString();
    }

    public static String getString(EditText edt) {
        return getString(edt, "");
    }

    public static int getInt(EditText edt) {
        return getInt(edt, 0);
    }

    public static boolean isEmpty(EditText edt) {
        return  edt.getText() == null || edt.getText().toString().length() == 0;
    }
}
