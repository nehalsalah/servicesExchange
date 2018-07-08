package com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class UserAcceptedThenAcceptTransactionCallback implements retrofit2.Callback<com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto> {
    @Override
    public void onResponse(Call<TransactionDto> call, Response<TransactionDto> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.User_Accepted_Then_Accept_Transaction, response.body()));
        } else {
            System.out.println("UserAcceptedThenAcceptTransactionCallback Nulllllllllll");
        }
    }

    @Override
    public void onFailure(Call<TransactionDto> call, Throwable t) {
        System.out.println("UserAcceptedThenAcceptTransactionCallback Eroooorrrrrrr/n");
        System.out.println("Error UserAcceptedThenAcceptTransactionCallback >>> " + t.getMessage());
    }
}
