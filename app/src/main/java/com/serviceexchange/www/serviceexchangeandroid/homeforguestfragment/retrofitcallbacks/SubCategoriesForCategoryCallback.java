package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SubCategoriesForCategoryCallback implements retrofit2.Callback<List<SkillDTO>> {
    @Override
    public void onResponse(Call<List<SkillDTO>> call, Response<List<SkillDTO>> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.SUBCATEGORIES_LIST, response.body()));
        else
            System.out.println("sub categories list is null");
    }

    @Override
    public void onFailure(Call<List<SkillDTO>> call, Throwable t) {
        System.out.println("sub categories list failed");
    }
}
