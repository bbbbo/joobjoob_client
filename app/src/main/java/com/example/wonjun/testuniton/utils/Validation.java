package com.example.wonjun.testuniton.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by wonjun on 2018. 1. 26..
 */

public class Validation {

    public static boolean validateFields(String name){

        if (TextUtils.isEmpty(name)) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateEmail(String string) {

        if (TextUtils.isEmpty(string) || !Patterns.EMAIL_ADDRESS.matcher(string).matches()) {

            return false;

        } else {

            return  true;
        }
    }
}
