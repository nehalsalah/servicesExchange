package com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsimplementations;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.ProfileApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.earning.retrofitCallbacks.EarningCallback;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.retrofitCallbacks.StaticCallback;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks.ServerLoginRegisterCallback;
import com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks.MyServiceCallback;
import com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks.RemoveServiceCallback;
import com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks.UpdateUserServiceCallback;
import com.serviceexchange.www.serviceexchangeandroid.myService.retrofitCallbacks.addServiceCallback;
import com.serviceexchange.www.serviceexchangeandroid.profile.retrofitCallbacks.updateUserDataCallback;

import java.util.Map;

import retrofit2.Retrofit;

public class NehalApiModelImpl implements NehalApiModelInt {

    Retrofit retrofit;

    public NehalApiModelImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void getUserServices(int userId, String type) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.getUserServices(userId, type,null).enqueue(new MyServiceCallback());
    }

    @Override
    public void deleteUserServices(Map sereviceData) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.removeService(sereviceData,null).enqueue(new RemoveServiceCallback());
    }

    @Override
    public void getUserStatic(int userId) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.getUserStatic(userId,null).enqueue(new StaticCallback());
    }

    @Override
    public void getEarningList(int userId) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.getEarningList(userId,null).enqueue(new EarningCallback());
    }

    @Override
    public void addService(ServiceDTO serviceDTO) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.addService(serviceDTO, null).enqueue(new addServiceCallback());

    }

    @Override
    public void updateUserData(UserDTO userDTO) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.updateUserData(userDTO, null).enqueue(new updateUserDataCallback());
    }

    @Override
    public void updateUserService(ServiceDTO serviceDTO) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.updateUserService(serviceDTO, null).enqueue(new UpdateUserServiceCallback());
    }
}
