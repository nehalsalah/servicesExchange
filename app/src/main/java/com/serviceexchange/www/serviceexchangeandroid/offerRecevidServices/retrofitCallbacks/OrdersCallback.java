package com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.retrofitCallbacks;

import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class OrdersCallback implements retrofit2.Callback<List<TransactionDto>> {
    @Override
    public void onResponse(Call<List<TransactionDto>> call, Response<List<TransactionDto>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.LIST_OF_ORDERS, response.body()));
            Log.i("orders", "call back should refresh");
        }
        else
            Log.i("callback", "nullllllllll");
    }

    @Override
    public void onFailure(Call<List<TransactionDto>> call, Throwable t) {
        Log.i("callback", t.getMessage());
    }
}
