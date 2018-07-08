package com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addServiceCallback implements Callback<ServiceDTO> {

    @Override
    public void onResponse(Call<ServiceDTO> call, Response<ServiceDTO> response) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.ADD_SERVICE, response.body()));
    }

    @Override
    public void onFailure(Call<ServiceDTO> call, Throwable t) {
        Log.i("FailureServerAddService", t.toString());

    }
}
