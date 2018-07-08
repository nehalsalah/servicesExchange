package com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks;

import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class RemoveServiceCallback implements  retrofit2.Callback<Boolean> {
    @Override
    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if (response.body() != null && response.body()) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.REMOVE_SERVICE, response.body()));
            System.out.println("MY SERVICE REMOVED : "+response.body());
        } else {
            System.out.println("delete RESPONSE is"+response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<Boolean> call, Throwable t) {
        Log.i("ServerMYSERVICEREMOVED", t.toString());
    }
}
