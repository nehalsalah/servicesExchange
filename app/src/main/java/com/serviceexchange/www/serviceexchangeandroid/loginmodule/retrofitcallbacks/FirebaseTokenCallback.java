package com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks;

import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class FirebaseTokenCallback implements retrofit2.Callback<Boolean> {
    @Override
    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if (response.body() == null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
    }

    @Override
    public void onFailure(Call<Boolean> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
    }
}
