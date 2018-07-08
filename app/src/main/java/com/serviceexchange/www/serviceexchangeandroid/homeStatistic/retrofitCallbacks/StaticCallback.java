package com.serviceexchange.www.serviceexchangeandroid.homeStatistic.retrofitCallbacks;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaticCallback implements retrofit2.Callback<UserStatics> {

    @Override
    public void onResponse(Call<UserStatics> call, Response<UserStatics> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.User_Statics, response.body()));
            System.out.println("User Statics Response is : "+response.body().toString());
        } else {
            System.out.println("User Statics Response is null");
        }
    }

    @Override
    public void onFailure(Call<UserStatics> call, Throwable t) {
        Log.i("UserStaticsResponse", t.toString());
    }
}
