package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ServicesPerCategoryPresenter implements ServicesPerCategoryMVP.Presenter {
    private ServicesPerCategoryMVP.View view;
    private OmarApiModelInt apiModel;

    public ServicesPerCategoryPresenter(OmarApiModelInt apiModelInt) {
        this.apiModel = apiModelInt;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(ServicesPerCategoryMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadRequestedServicesList(int categoryId) {
        apiModel.loadRequestedServicesForCategory(categoryId);
    }

    @Override
    public void loadOfferedServicesList(int categoryId, int i) {
        apiModel.loadOfferedServicesForCategory(categoryId, i);
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
        if (event.getEventType() == CustomEventType.OFFERED_SERVICES_FOR_CATEGORIES_LIST)
            view.setOfferedServicesList((List<ServiceDTO>) event.getObject());
        else if (event.getEventType() == CustomEventType.REQUESTED_SERVICES_FOR_CATEGORIES_LIST)
            view.setRequestedServicesList((List<ServiceDTO>) event.getObject());
    }

    @Override
    public void serviceCardOnClickAction(ServiceDTO service) {
        view.startServiceDetailFragment(service);
    }

}
