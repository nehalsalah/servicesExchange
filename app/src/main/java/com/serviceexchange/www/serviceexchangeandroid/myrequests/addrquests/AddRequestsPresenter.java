package com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseInt;
import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseManager;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class AddRequestsPresenter implements AddRquestsMVP.Presenter {

    @Nullable
    private AddRquestsMVP.View view;
    private EslamApiModelInt apiModelInt;
    FirebaseInt firebaseInt;

    public AddRequestsPresenter(EslamApiModelInt apiModelInt) {
        this.apiModelInt = apiModelInt;
        EventBus.getDefault().register(this);
        firebaseInt= new FirebaseManager();
    }

    @Override
    public void setView(AddRquestsMVP.View view) {
        this.view = view;
    }

    @Override
    public void addMyRequests(ServiceDTO serviceDTO) {
        apiModelInt.addReq(serviceDTO);
    }


    @Override
    public void cash_add_request_Clicked() {
        view.setRequestTypeCash();
    }

    @Override
    public void exchange_add_request_Clicked() {
        view.setRequestTypeExchange();
    }

    @Override
    public void button_attach_file_Clicked() {
        view.attachFile();
    }

    @Override
    public void publish_request_Clicked() {
        view.publishRequest();
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
    public void uploadFile(Uri selectedImage) {
        firebaseInt.uploadFile(selectedImage);
    }

    @Subscribe
    public void ServiceAfterAdd(CustomEvent event) {
        if (event.getEventType() == CustomEventType.ADD_REQ)
            view.serviveObjectBack((ServiceDTO) event.getObject());
        else if (event.getEventType() == CustomEventType.FILE_URL) {
            view.setFileUrl((String) event.getObject());
        }
    }
}
