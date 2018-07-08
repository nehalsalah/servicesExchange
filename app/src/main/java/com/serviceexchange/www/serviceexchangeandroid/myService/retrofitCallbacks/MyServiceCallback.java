package com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MyServiceCallback implements retrofit2.Callback<List<ServiceDTO>> {

    @Override
    public void onResponse(Call<List<ServiceDTO>> call, Response<List<ServiceDTO>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.MY_SERVICES_LIST, response.body()));
            System.out.println("MY SERVICE list size is : "+response.body().size());
        } else {
            System.out.println(" MY service list is null");
        }
    }

    @Override
    public void onFailure(Call<List<ServiceDTO>> call, Throwable t) {
        Log.i("ServerListOfMyService", t.toString());
    }
}
