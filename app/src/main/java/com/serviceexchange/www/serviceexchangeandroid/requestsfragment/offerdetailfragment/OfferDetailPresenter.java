package com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment;

import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigInteger;

public class OfferDetailPresenter implements OfferDetailMVP.Presenter {

    private static final String TAG = "SEND_OFFER";
    private OfferDetailMVP.View view;
    private OmarApiModelInt apiModel;
    private SharedPreferencesModelInt sharedPreferencesModel;
    private UserDTO user;

    public OfferDetailPresenter(OmarApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        EventBus.getDefault().register(this);
        user = sharedPreferencesModel.getCurrentUserFromSharedPreferences();
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
        if (event.getEventType() == CustomEventType.STARTED_TRANS) {
            view.transStartedSuccess();
            Log.i("callback", "should call finish");
        }
        else if (event.getEventType() == CustomEventType.NULL_RESPONSE_BODY)
            Log.i(TAG, "null");
    }

    @Override
    public void setView(OfferDetailMVP.View view) {
        this.view = view;
    }

    @Override
    public void sendOfferButtonAction() {
        ServiceDTO service = view.getService();
        if (!user.getId().equals(service.getUo().getId())) {
            if (checkInputValidity()) {
                TransactionDto trans = new TransactionDto();
                trans.setDuration(BigInteger.valueOf(Integer.parseInt(view.getDurationText())));
                trans.setPrice(Integer.parseInt(view.getPointsText()));
                trans.setServiceId(service.getId());
                trans.setServiceName(service.getName());
                trans.setsByUser(user.getId());
                apiModel.sendOffer(trans);
            }
        } else {
            view.cantSendOffer();
        }
    }

    private boolean checkInputValidity() {
        try {
            Integer points = Integer.parseInt(view.getPointsText());
            Integer duration = Integer.parseInt(view.getDurationText());
            String desc = view.getDescText();
            if (points == 0 || duration == 0 || desc.length() == 0)
                return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
