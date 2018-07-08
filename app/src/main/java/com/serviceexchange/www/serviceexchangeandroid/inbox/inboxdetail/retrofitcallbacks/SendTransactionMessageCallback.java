package com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class SendTransactionMessageCallback implements retrofit2.Callback<MessageGeneralDto> {
    @Override
    public void onResponse(Call<MessageGeneralDto> call, Response<MessageGeneralDto> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.SEND_TRANSACTION_MESSAGES, response.body()));
        } else {
            System.out.println("SendTransactionMessageCallback Nulllllllllll");
        }
    }

    @Override
    public void onFailure(Call<MessageGeneralDto> call, Throwable t) {
        System.out.println("SendTransactionMessageCallback Eroooorrrrrrr");
    }
}
