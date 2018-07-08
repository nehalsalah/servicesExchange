package com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Token;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class ServerTokenCallback implements retrofit2.Callback<com.serviceexchange.www.serviceexchangeandroid.pojos.Token> {
    @Override
    public void onResponse(Call<Token> call, Response<Token> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.SERVER_TOKEN, response.body()));
        else
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
    }

    @Override
    public void onFailure(Call<Token> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
    }
}
