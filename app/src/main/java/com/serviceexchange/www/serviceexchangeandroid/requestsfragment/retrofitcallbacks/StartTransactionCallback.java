package com.serviceexchange.www.serviceexchangeandroid.requestsfragment.retrofitcallbacks;

import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class StartTransactionCallback implements retrofit2.Callback<com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto> {
    @Override
    public void onResponse(Call<TransactionDto> call, Response<TransactionDto> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.STARTED_TRANS, response.body()));
            Log.i("callback", "should send event");
        }
        else {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
            Log.i("callback", "should be null");
        }
    }

    @Override
    public void onFailure(Call<TransactionDto> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
        Log.i("callback", "in fail" + t.toString());
    }
}
