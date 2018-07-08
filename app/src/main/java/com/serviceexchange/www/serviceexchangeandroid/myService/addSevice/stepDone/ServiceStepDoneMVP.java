package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepDone;

import com.service_exchange.api_services.dao.dto.ServiceDTO;

public interface ServiceStepDoneMVP {
    interface View {

        void addService();

    }

    interface Presenter {

        void setView(ServiceStepDoneMVP.View view);
        void register();

        void terminate();
        void addService(ServiceDTO serviceDTO);
       // void editService(ServiceDTO serviceDTO);

    }
    interface Model {

    }
}
