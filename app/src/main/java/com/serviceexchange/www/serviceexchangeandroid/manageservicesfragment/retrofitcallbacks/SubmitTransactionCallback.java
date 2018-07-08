package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks;

import android.util.Log;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginActivity;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class SubmitTransactionCallback implements retrofit2.Callback<com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto> {
    @Override
    public void onResponse(Call<TransactionDto> call, Response<TransactionDto> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.SUBMITTED_TRANS, response.body()));
        else
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
    }

    @Override
    public void onFailure(Call<TransactionDto> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
    }
}
