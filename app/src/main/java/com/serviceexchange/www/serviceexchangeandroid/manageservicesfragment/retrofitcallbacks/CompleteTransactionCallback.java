package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CompleteTransactionCallback implements retrofit2.Callback<java.util.List<com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto>> {
    @Override
    public void onResponse(Call<List<TransactionDto>> call, Response<List<TransactionDto>> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.COMPLETED_SERVICES_TRANS, response.body()));
        else
            EventBus.getDefault().post(new CustomEvent(CustomEventType.NULL_RESPONSE_BODY, response.body()));
    }

    @Override
    public void onFailure(Call<List<TransactionDto>> call, Throwable t) {
        EventBus.getDefault().post(new CustomEvent(CustomEventType.FAILED_REQUEST, t));
    }
}
