package com.nanobubble.android.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String MAIN_SHARED_PREF = "main_shared_pref";

    private static Prefs mInstance;
    private Context mCtx;

    private Prefs(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized Prefs getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new Prefs(mCtx);
        }
        return mInstance;
    }

    public void saveLogin(String phone, Boolean status) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(MAIN_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();

        editor.putBoolean("login", status);
        editor.putString("last_phone", phone);

        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(MAIN_SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login", false);
    }

    public void setLogin(Boolean status){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(MAIN_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("login", status);

        editor.apply();
    }

    public String getLastPhone(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(MAIN_SHARED_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString("last_phone", "");
    }


}
