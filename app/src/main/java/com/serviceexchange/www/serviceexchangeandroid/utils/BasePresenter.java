package com.serviceexchange.www.serviceexchangeandroid.utils;

import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.TimelineMVP;

import org.greenrobot.eventbus.Subscribe;

public interface BasePresenter {
    void reRegister();
    void terminate();
    @Subscribe void onPostEvent(CustomEvent event);
}
