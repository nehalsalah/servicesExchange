package com.serviceexchange.www.serviceexchangeandroid.requestsfragment;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class RequestsPresenter implements RequestsMVP.Presenter {

    private RequestsMVP.View view;
    private OmarApiModelInt apimodel;

    public RequestsPresenter(OmarApiModelInt apimodel) {
        this.apimodel = apimodel;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(RequestsMVP.View view) {
        this.view = view;
    }

    @Override
    public void offerButtonClickAction(ServiceDTO service) {
        view.showOfferDetailsFragment(service);
    }

    @Override
    public void loadAllRequests(int page) {
        apimodel.loadAllRequests(page);
    }

    @Override
    public void reRegister() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    @Override
    public void onPostEvent(CustomEvent event) {
        if (event.getEventType() == CustomEventType.ALL_REQUEST_LIST) {
            view.setList((List<ServiceDTO>) event.getObject());
        }
    }
}
