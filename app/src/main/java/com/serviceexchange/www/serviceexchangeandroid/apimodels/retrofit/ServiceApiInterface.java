package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceApiInterface {

    // actually used methods ///////////////////////////////////////////////////////////////////////

    @GET("none")
    Call<List<ReviewDTO>> loadReviewsForService(@Query("serviceId") Integer id
            , @Query("serviceId") int page);

    @GET("service/getReq")
    Call<List<ServiceDTO>> loadAllRequests(@Query("page") int page);
}
