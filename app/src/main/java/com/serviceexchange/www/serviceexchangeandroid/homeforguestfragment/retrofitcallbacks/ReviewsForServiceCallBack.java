package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ReviewsForServiceCallBack implements retrofit2.Callback<List<ReviewDTO>> {
    @Override
    public void onResponse(Call<List<ReviewDTO>> call, Response<List<ReviewDTO>> response) {
        if (response.body() != null)
            EventBus.getDefault().post(new CustomEvent(CustomEventType.REVIEWS_FOR_SERVICES, response.body()));
    }

    @Override
    public void onFailure(Call<List<ReviewDTO>> call, Throwable t) {

    }
}
