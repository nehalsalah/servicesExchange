package com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetReqCallback implements retrofit2.Callback<List<ServiceDTO>> {
    @Override
    public void onResponse(Call<List<ServiceDTO>> call, Response<List<ServiceDTO>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.GET_REQ, response.body()));
        } else {
            System.out.println("GetReqCallback Nulllllllllll");
        }
        System.out.println("GetReqCallback Nulllllllllll");
    }

    @Override
    public void onFailure(Call<List<ServiceDTO>> call, Throwable t) {
        System.out.println("GetReqCallback Eroooorrrrrrr/n");
        System.out.println("Error GetReqCallback >>> " + t.toString());

    }
}
