package com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AllTransactionMessagesCallback implements retrofit2.Callback<List<MessageGeneralDto>> {
    @Override
    public void onResponse(Call<List<MessageGeneralDto>> call, Response<List<MessageGeneralDto>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.ALL_TRANSACTION_MESSAGES, response.body()));
        } else {
            System.out.println("AllTransactionMessagesCallback Nulllllllllll");
        }
    }

    @Override
    public void onFailure(Call<List<MessageGeneralDto>> call, Throwable t) {
        System.out.println("AllTransactionMessagesCallback Eroooorrrrrrr");
    }
}
