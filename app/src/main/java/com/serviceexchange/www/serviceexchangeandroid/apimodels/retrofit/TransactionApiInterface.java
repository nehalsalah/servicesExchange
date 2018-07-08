package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TransactionApiInterface {

//    @POST("Transaction/sendMessage")
//    Call<Boolean> sendMessage(@Body int transactionId, @Body Message message, @Header("token") String token);

    @GET("transaction/getUserActiveTransactions/{userId}/{pageNum}")
    Call<List<TransactionDto>> loadActiveServicesForUer(@Path("userId") int userId
            , @Path("pageNum") int page
            , @Header("token") String token);

    @POST("transaction/completeTransaction")
    Call<TransactionDto> submitTransactionForConfirmation(@Body TransactionDto trans, @Header("token") String token);

    @GET("transaction/getUserCompletedTransactions/{userId}/{pageNum}")
    Call<List<TransactionDto>> loadPendingTransactionsForUser(@Path("userId") int userId
            , @Path("pageNum") int pageNum
            , @Header("token") String token);

    @GET("transaction/getUserCompletedAndApprovedTransactions/{userId}/{pageNum}")
    Call<List<TransactionDto>> loadCompletedTransactionsForUser(@Path("userId") int userId
            , @Path("pageNum") int pageNum
            , @Header("token") String token);

    @POST("transaction/makeTransactionOnService")
    Call<TransactionDto> sendOfferOnService(@Body TransactionDto transactionDto, @Header("token") String token);

    @POST("transaction/approveCompletedTransaction")
    Call<TransactionDto> approveTransaction(@Body Map<String, Object> map);

    @GET("user/getUserIncomingReq")
    Call<List<TransactionDto>> loadOrders(@Query("userId") int userId);

    @POST("transaction/userRejectTransaction")
    Call<TransactionDto> rejectTransaction(@Body TransactionDto transactionDto);

}
