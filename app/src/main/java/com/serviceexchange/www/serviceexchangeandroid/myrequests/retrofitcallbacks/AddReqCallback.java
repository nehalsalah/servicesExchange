package com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Response;

public class AddReqCallback implements retrofit2.Callback<ServiceDTO> {
    @Override
    public void onResponse(Call<ServiceDTO> call, Response<ServiceDTO> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.ADD_REQ, response.body()));
        } else {
            System.out.println("AddReqCallback Nulllllllllll");
        }
    }

    @Override
    public void onFailure(Call<ServiceDTO> call, Throwable t) {
        System.out.println("AddReqCallback Eroooorrrrrrr/n");
        System.out.println("Error AddReqCallback >>> " + t.getMessage());
    }
}
