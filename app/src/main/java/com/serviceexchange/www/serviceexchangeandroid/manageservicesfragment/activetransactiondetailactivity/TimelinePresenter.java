package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity;

import android.net.Uri;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class TimelinePresenter implements TimelineMVP.Presenter {
    private TimelineMVP.View view;
    private FirebaseInt firebaseManager;
    private OmarApiModelInt apiModel;
    private SharedPreferencesModelInt preferencesModel;

    public TimelinePresenter(FirebaseInt firebaseManager, OmarApiModelInt apiModel) {
        this.firebaseManager = firebaseManager;
        this.apiModel = apiModel;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(TimelineMVP.View view) {
        this.view = view;
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

    @Override
    public void deliverNowAction() {
        view.showUploadAndSubmitDialogue();
    }

    @Override
    public void chatAction() {
        view.goToChatBox();
    }

    @Override
    public void uploadButtonAction() {
        view.startFileChooser();
    }

    @Override
    public void uploadFile(Uri uri) {
        firebaseManager.uploadFile(uri);
    }

    @Override
    public void submitButtonAction(TransactionDto trans) {
        apiModel.submitTransaction(trans);
    }

    @Subscribe
    public void onPostEvent(CustomEvent event) {
        if (event.getEventType() == CustomEventType.FILE_URL) {
            view.setFileUrl((String) event.getObject());
        } else if (event.getEventType() == CustomEventType.SUBMITTED_TRANS) {
            view.submitSuccess();
        }
    }
}
