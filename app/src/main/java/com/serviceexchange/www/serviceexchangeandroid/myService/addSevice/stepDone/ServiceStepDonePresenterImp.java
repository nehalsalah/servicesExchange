package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone;

import android.content.Context;
import android.util.Log;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne.AddServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ServiceStepDonePresenterImp implements ServiceStepDoneMVP.Presenter {

    ServiceStepDoneMVP.View view;
    NehalApiModelInt apiModel;
    SharedPreferencesModelInt sharedPreferencesModel;

    public ServiceStepDonePresenterImp(NehalApiModelInt apiModel, Context context) {
        this.apiModel = apiModel;
        sharedPreferencesModel = SharedPreferencesModel.getInstance(context);
        EventBus.getDefault().register(this);
    }


    @Override
    public void setView(ServiceStepDoneMVP.View view) {
        this.view = view;
    }

    @Subscribe
    public void servicesOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.ADD_SERVICE) {
            Log.i("response", "service added dto" + ((ServiceDTO) event.getObject()).getName());
            apiModel.getUserServices(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId(), "offerd");
        }
//        else if (event.getEventType() == CustomEventType.Update_SERVICE) {
//
//            // view.setRemovedMyServicesList((Boolean) event.getObject());
//        }
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
    public void addService(ServiceDTO serviceDTO) {
        apiModel.addService(serviceDTO);
    }

//    @Override
//    public void editService(ServiceDTO serviceDTO) {
//        apiModel.updateUserService(serviceDTO);
//    }

}
