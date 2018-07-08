package com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerLoginRegisterCallback implements Callback<UserDTO> {
    @Override
    public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.USER, response.body()));
        else
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
    }

    @Override
    public void onFailure(Call<UserDTO> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
    }
}
