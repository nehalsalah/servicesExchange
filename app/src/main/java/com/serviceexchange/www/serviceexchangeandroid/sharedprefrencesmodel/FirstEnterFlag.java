package com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel;

import android.content.Context;
import android.content.SharedPreferences;

public class FirstEnterFlag implements FirstEnterFlagInt {

    private static final String FirstFlag = "firstFlag";
    private static FirstEnterFlag instance;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private Context context;

    private FirstEnterFlag() {
    }

    private FirstEnterFlag(Context context) {
        this.context = context;
    }

    public static FirstEnterFlag getInstance(Context context) {
        if (instance == null) {
            instance = new FirstEnterFlag(context);
            preferences = context.getSharedPreferences(FirstFlag, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return instance;
    }

    @Override
    public void putFirstEnterFlag(int flag) {

        editor.putInt("Fflag", flag);
        editor.commit();
    }

    @Override
    public int getFirstEnterFlag() {
        int flag = preferences.getInt("Fflag", 0);
        return flag;
    }
}
