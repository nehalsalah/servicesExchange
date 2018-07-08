package com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices;

import android.content.SharedPreferences;
import android.util.Log;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.earning.Pojo;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class OfferReceivedPresenterImp implements OfferReceivedMVP.Presenter {


    OmarApiModelInt apiModel;
    SharedPreferencesModelInt sharedPreferencesModel;
    OfferReceivedMVP.View view;
    UserDTO user;

    public OfferReceivedPresenterImp(OmarApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        user = sharedPreferencesModel.getCurrentUserFromSharedPreferences();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void OnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.LIST_OF_ORDERS) {
            view.setOfferReceivedList((List<TransactionDto>) event.getObject());
            Log.i("orders", "presenter should refresh");
        } else if (event.getEventType() == CustomEventType.User_Accepted_Then_Accept_Transaction
                || event.getEventType() == CustomEventType.USER_REJECTED_TRANS) {
            view.reload();
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
    public void setView(OfferReceivedMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadOfferReceivedList() {
        apiModel.loadOrders(user);
    }

    @Override
    public void acceptOfferClicked(TransactionDto trans) {
        apiModel.acceptOffer(user, trans);
    }

    @Override
    public void cancelOfferClicked(TransactionDto trans) {
        apiModel.cancelOffer(user, trans);
    }


}
