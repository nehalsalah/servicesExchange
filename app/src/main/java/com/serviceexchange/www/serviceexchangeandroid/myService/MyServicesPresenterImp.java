package com.serviceexchange.www.serviceexchangeandroid.myService;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MyServicesPresenterImp implements MyServiceMVP.Presenter {

    NehalApiModelInt apiModel;
    SharedPreferencesModelInt sharedPreferencesModel;
    MyServiceMVP.View view;
    SharedPreferencesModelInt getSharedPreferencesModel;

    public MyServicesPresenterImp(NehalApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        EventBus.getDefault().register(this);
        getSharedPreferencesModel= SharedPreferencesModel.getInstance(getApplicationContext());
    }

    @Subscribe
    public void servicesListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.MY_SERVICES_LIST)
            view.setMyServicesList((List<ServiceDTO>) event.getObject());
        else if (event.getEventType() == CustomEventType.REMOVE_SERVICE)
            view.setRemovedMyServicesList((Boolean) event.getObject());
    }
    @Override
    public void register() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setView(MyServiceMVP.View view) {
        this.view=view;
    }

    @Override
    public void emptyList() {
        view.setEmptyService();
    }

    @Override
    public void removeService(Map serviceData) {
        apiModel.deleteUserServices(serviceData);
    }


    @Override
    public void loadMyServicesList() {
        Log.i("id",sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId()+"iddddd");
        apiModel.getUserServices(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId(), "offerd");
    }

}
