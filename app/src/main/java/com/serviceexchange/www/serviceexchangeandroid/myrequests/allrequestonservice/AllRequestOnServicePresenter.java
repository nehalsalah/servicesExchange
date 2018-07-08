package com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice;

import android.support.annotation.Nullable;

import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class AllRequestOnServicePresenter implements AllRequestOnServiceMVP.Presenter {

    @Nullable
    private AllRequestOnServiceMVP.View view;
    private EslamApiModelInt apiModelInt;

    public AllRequestOnServicePresenter(EslamApiModelInt apiModelInt) {
        this.apiModelInt = apiModelInt;
    }

    @Override
    public void setView(AllRequestOnServiceMVP.View view) {
        this.view = view;
    }

    @Override
    public void onAcceptOfferClicked(TransactionDto transactionDto) {
        apiModelInt.userAcceptedThenAcceptTransaction(transactionDto);
    }

    @Override
    public void loadAllRequstOnService(int serviceId) {
        apiModelInt.getAllRequstOnService(serviceId);
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
    public void TransactionListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.Get_All_Requst_On_Service)
            view.setTransactionList((List<TransactionEslam>) event.getObject());
        if (event.getEventType() == CustomEventType.User_Accepted_Then_Accept_Transaction)
            view.acceptOffer((TransactionDto) event.getObject());

    }
}
