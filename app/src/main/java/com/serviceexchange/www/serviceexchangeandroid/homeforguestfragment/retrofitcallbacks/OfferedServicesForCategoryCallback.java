package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class OfferedServicesForCategoryCallback implements retrofit2.Callback<List<ServiceDTO>> {
    @Override
    public void onResponse(Call<List<ServiceDTO>> call, Response<List<ServiceDTO>> response) {
        if (response.body() != null) {
            EventBus.getDefault().post(new CustomEvent(CustomEventType.OFFERED_SERVICES_FOR_CATEGORIES_LIST, response.body()));
            System.out.println("----------->service for categories dixe---------->"+response.body().size());
        } else {
            System.out.println("----------->service for categories list is null");
        }
    }

    @Override
    public void onFailure(Call<List<ServiceDTO>> call, Throwable t) {
        System.out.println("----------->service for categories list onFailure");
    }
}
