package com.serviceexchange.www.serviceexchangeandroid.inbox.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AllUserTransactionChatsCallback implements retrofit2.Callback<List<TransactionChatDto>> {

    @Override
    public void onResponse(Call<List<TransactionChatDto>> call, Response<List<TransactionChatDto>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.ALL_USER_TRANSACTION_CHATS, response.body()));
        } else {
            System.out.println("AllUserTransactionChatsCallback Nulllllllllll");
        }
    }

    @Override
    public void onFailure(Call<List<TransactionChatDto>> call, Throwable t) {
        System.out.println("AllUserTransactionChatsCallback Eroooorrrrrrr/n");
        System.out.println("Error Message >>> " + t.getMessage());
    }
}
