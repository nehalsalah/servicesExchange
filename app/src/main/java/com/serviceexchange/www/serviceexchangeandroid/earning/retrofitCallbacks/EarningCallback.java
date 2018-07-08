package com.serviceexchange.www.serviceexchangeandroid.earning.retrofitCallbacks;

import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarningCallback implements Callback<List<Earning>> {

    @Override
    public void onResponse(Call<List<Earning>> call, Response<List<Earning>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.User_Earning, response.body()));
            System.out.println("User Earning Response is : "+response.body().toString());
        } else {
            System.out.println("User Earning Response is null");
        }
    }

    @Override
    public void onFailure(Call<List<Earning>> call, Throwable t) {
        Log.i("UserEarningResponse", t.toString());
    }
}
