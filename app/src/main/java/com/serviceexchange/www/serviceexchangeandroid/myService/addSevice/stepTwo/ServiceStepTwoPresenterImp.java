package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ServiceStepTwoPresenterImp implements ServiceStepTwoMVP.Presenter {

    ServiceStepTwoMVP.View view;

    public ServiceStepTwoPresenterImp() {

    }


    @Override
    public void setView(ServiceStepTwoMVP.View view) {
        this.view=view;
    }

}
