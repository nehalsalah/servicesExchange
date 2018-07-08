package com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetAllRequstOnServiceCallback implements retrofit2.Callback<List<TransactionEslam>> {
    @Override
    public void onResponse(Call<List<TransactionEslam>> call, Response<List<TransactionEslam>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.Get_All_Requst_On_Service, response.body()));
        } else {
            System.out.println("GetAllRequstOnServiceCallback Nulllllllllll");
        }
        System.out.println("GetAllRequstOnServiceCallback onresponse/n");
    }

    @Override
    public void onFailure(Call<List<TransactionEslam>> call, Throwable t) {
        System.out.println("GetAllRequstOnServiceCallback Eroooorrrrrrr/n");
        System.out.println("Error GetAllRequstOnServiceCallback >>> " + t.getMessage());
    }
}
