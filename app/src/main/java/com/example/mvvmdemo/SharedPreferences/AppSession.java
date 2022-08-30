package com.example.mvvmdemo.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvvmdemo.auth.model.UserLoginResponse;
import com.google.gson.Gson;

public class AppSession {
    public static AppSession yourPreference;
    public SharedPreferences sharedPreferences;

    public static AppSession getInstance(Context context) {
        if (yourPreference == null) {
            yourPreference = new AppSession(context);
        }
        return yourPreference;
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public AppSession(Context context) {
        sharedPreferences = context.getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setValue(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.apply();
    }

    public String getValue(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, defaultValue);
        }
        return false;
    }

    public UserLoginResponse getUser() {
        return (UserLoginResponse) getObject(Constant.USER, UserLoginResponse.class);
    }



    public Object getObject(String key, Class<?> classOfT) {
        String json = getString(key);
        return new Gson().fromJson(json, classOfT);
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putObject(String key, Object obj) {
        checkForNullKey(key);
        Gson gson = new Gson();
        putString(key, gson.toJson(obj));
    }


    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullValue(value);
        sharedPreferences.edit().putString(key, value).apply();
    }

    public boolean checkForNullKey(String key) {
        return key == null;
    }

    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

}
