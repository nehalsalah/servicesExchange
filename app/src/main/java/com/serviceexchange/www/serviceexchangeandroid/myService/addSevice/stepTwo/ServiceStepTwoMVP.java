package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepTwo;

import com.service_exchange.api_services.dao.dto.SkillDTO;

import java.util.List;

public interface ServiceStepTwoMVP {
    interface View {

        void addService();

    }

    interface Presenter {

        void setView(ServiceStepTwoMVP.View view);

    }
    interface Model {

    }
}
