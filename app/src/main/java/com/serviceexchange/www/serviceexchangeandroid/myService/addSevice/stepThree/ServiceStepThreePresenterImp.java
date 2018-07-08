package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree;

import android.net.Uri;

import com.serviceexchange.www.serviceexchangeandroid.firebasemanager.FirebaseInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ServiceStepThreePresenterImp implements ServiceStepThreeMVP.Presenter {

    ServiceStepThreeMVP.View view;
    FirebaseInt firebaseInt;
    public ServiceStepThreePresenterImp(FirebaseInt firebaseInt) {
        this.firebaseInt=firebaseInt;
        EventBus.getDefault().register(this);

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
    public void uploadFile(Uri uri) {
        firebaseInt.uploadFile(uri);
    }

    @Override
    public void setView(ServiceStepThreeMVP.View view) {
        this.view=view;
    }

    @Subscribe
    public void onPostEvent(CustomEvent event) {
        if (event.getEventType() == CustomEventType.FILE_URL) {
            view.setFileUrl((String) event.getObject());
        }
    }
}
