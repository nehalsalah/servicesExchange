package com.serviceexchange.www.serviceexchangeandroid.earning;

import android.content.SharedPreferences;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class EarningPresenterImp implements EarningMVP.Presenter {


    NehalApiModelInt apiModel;
    SharedPreferencesModelInt sharedPreferencesModel;
    EarningMVP.View view;

    public EarningPresenterImp(NehalApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void OnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.User_Earning) {
            view.setEarningList((List<Earning>) event.getObject());
        }
        else if (event.getEventType() == CustomEventType.User_Statics) {
            view.setPoints((UserStatics) event.getObject());
        }
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
    public void setView(EarningMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadEarningList() {
        apiModel.getEarningList(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId());
    }

    @Override
    public void loadPoints() {
        apiModel.getUserStatic(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId());
    }

    @Override
    public void emptyList() {
        view.setEmptyText();
    }

}
