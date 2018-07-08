package com.serviceexchange.www.serviceexchangeandroid.myrequests;

import android.support.annotation.Nullable;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MyRequestsPresenter implements MyRequestsMVP.Presenter {

    @Nullable
    private MyRequestsMVP.View view;
    private EslamApiModelInt apiModelInt;

    public MyRequestsPresenter(EslamApiModelInt apiModelInt) {
        this.apiModelInt = apiModelInt;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(MyRequestsMVP.View view) {
        this.view = view;
    }

    @Override
    public void onCardListItemClicked() {
        view.goToWhoRequest();
    }

    @Override
    public void loadAllMyRequests(int page) {
        apiModelInt.getReq(page, "requested");
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

    @Subscribe
    public void ServiceListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.GET_REQ)
            view.setServiceList((List<ServiceDTO>) event.getObject());
    }
}
