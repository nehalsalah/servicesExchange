package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageApiInterface {

    @GET("message/getAllUserTransactionChats/{userId}/{pageNum}")
    Call<List<TransactionChatDto>> getAllUserTransactionChats(
            @Path("userId") int userId,
            @Path("pageNum") int pageNum
    );

    @GET("message/getAllTransactionMessages/{transactionId}/{userId}/{pageNum}")
    Call<List<MessageGeneralDto>> getAllTransactionMessages(
            @Path("transactionId") int transactionId,
            @Path("userId") int userId,
            @Path("pageNum") int pageNum
    );

    @POST("message/sendTransactionMessage")
    Call<MessageGeneralDto> sendTransactionMessage(@Body MessageGeneralDto messageGeneralDto);
}
