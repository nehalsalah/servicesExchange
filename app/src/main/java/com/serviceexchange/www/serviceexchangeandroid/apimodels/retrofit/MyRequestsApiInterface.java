package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyRequestsApiInterface {

    @GET("user/getUserServices")
    Call<List<ServiceDTO>> getReq(@Query("userId") int userId, @Query("type") String type);

    @POST("user/addService")
    Call<ServiceDTO> addReq(@Body ServiceDTO serviceDTO);

    @GET("service/getAllRequstOnService")
    Call<List<TransactionEslam>> getAllRequstOnService(@Query("serviceId") int serviceId);

    @POST("transaction/userAcceptedThenAcceptTransaction")
    Call<TransactionDto> userAcceptedThenAcceptTransaction(@Body TransactionDto transactionDto);
}
