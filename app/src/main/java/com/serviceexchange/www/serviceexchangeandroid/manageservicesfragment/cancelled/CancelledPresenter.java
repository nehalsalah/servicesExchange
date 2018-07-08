package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class CancelledPresenter implements CancelledMVP.Presenter {
    private CancelledMVP.View view;
    private OmarApiModelInt apiModel;
    private SharedPreferencesModelInt sharedPrefsModel;
    private UserDTO user;

    public CancelledPresenter(OmarApiModelInt apiModel, SharedPreferencesModelInt sharedPrefsModel) {
        this.apiModel = apiModel;
        this.sharedPrefsModel = sharedPrefsModel;
        EventBus.getDefault().register(this);
        user = sharedPrefsModel.getCurrentUserFromSharedPreferences();
    }

    @Override
    public void setView(CancelledMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadPendingServices(int i) {
        apiModel.loadPendingTransactions(user, i);
    }

    @Override
    public void cardOnClickAction(TransactionDto trans) {
        view.showPendingTransactionActivity(trans);
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
        if (event.getEventType() == CustomEventType.PENDING_SERVICES_TRANS)
            view.setList((List<TransactionDto>) event.getObject());
    }
}
