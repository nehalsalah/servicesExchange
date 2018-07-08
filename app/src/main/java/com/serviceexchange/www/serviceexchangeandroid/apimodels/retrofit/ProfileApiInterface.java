package com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Token;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserAuth;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfileApiInterface {

    @POST("user/logInOrSignup")
    Call<UserDTO> logInOrSignup(@Body UserDTO user);

    @POST("/auth")
    Call<Token>  getServerToken(@Body UserAuth userAuth);

    @POST("user/setUserFirebaseId")
    Call<Boolean> refreshFirebaseToken(@Body Map<String, Object> map);

    //nehal add here
    @GET("user/getUserServices")
    Call<List<ServiceDTO>> getUserServices(@Query("userId") int userId, @Query("type") String type, @Header("token") String token);

    @POST("user/removeService")
    Call<Boolean> removeService(@Body Map serviceData, @Header("token") String token);

    @GET("user/getUserStatic")
    Call<UserStatics> getUserStatic(@Query("userId") int userId, @Header("token") String token);

    @GET("user/getEarningList")
    Call<List<Earning>> getEarningList(@Query("userId") int userId, @Header("token") String token);

    @POST("user/addService")
    Call<ServiceDTO> addService(@Body ServiceDTO serviceDTO, @Header("token") String token);

    @POST("user/updateUserData")
    Call<Boolean> updateUserData(@Body UserDTO userDTO, @Header("token") String token);

    @POST("user/updateUserService")
    Call<Boolean> updateUserService(@Body ServiceDTO serviceDTO, @Header("token") String token);
}
