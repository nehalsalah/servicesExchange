package com.serviceexchange.www.serviceexchangeandroid.requestsfragment.retrofitcallbacks;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RequestsCallback implements retrofit2.Callback<java.util.List<com.service_exchange.api_services.dao.dto.ServiceDTO>> {
    @Override
    public void onResponse(Call<List<ServiceDTO>> call, Response<List<ServiceDTO>> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.ALL_REQUEST_LIST, response.body()));
        else
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
    }

    @Override
    public void onFailure(Call<List<ServiceDTO>> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
        Log.i("CALLBACK", t.toString());
    }
}
