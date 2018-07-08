package com.serviceexchange.www.serviceexchangeandroid.homeStatistic;

import android.content.SharedPreferences;

import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


import static com.facebook.FacebookSdk.getApplicationContext;

public class StatisticPresenterImp implements StatisticMVP.Presenter {

    NehalApiModelInt apiModel;
    SharedPreferencesModelInt sharedPreferencesModel;
    StatisticMVP.View view;

    public StatisticPresenterImp(NehalApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void OnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.User_Statics)
            view.setUserStatics((UserStatics) event.getObject());
    }

    @Override
    public void register() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void loadUserStatics() {
        apiModel.getUserStatic(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId());
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setView(StatisticMVP.View view) {
        this.view = view;
    }
}
