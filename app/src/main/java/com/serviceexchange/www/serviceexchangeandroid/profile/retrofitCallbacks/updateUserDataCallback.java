package com.serviceexchange.www.serviceexchangeandroid.profile.retrofitCallbacks;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateUserDataCallback implements Callback<Boolean> {

    @Override
    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if (response.body() != null && response.body()) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.updateUserData, response.body()));
            System.out.println("update User Data RESPONSE : "+response.body());
        } else {
            System.out.println("update User Data RESPONSE is"+response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<Boolean> call, Throwable t) {
        Log.i("updateUserDataRESPONSE", t.toString());
    }
}
