package com.serviceexchange.www.serviceexchangeandroid.myService;

import com.service_exchange.api_services.dao.dto.ServiceDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MyServiceMVP {
    interface View {
        void navigateToDisplayService();

        void setMyServicesList(List<ServiceDTO> object);

        void setEmptyService();
        void setRemovedMyServicesList(Boolean object);
    }

    interface Presenter {

        void loadMyServicesList();

        void register();

        void terminate();

        void setView(View view);

        void emptyList();

        void removeService(Map serviceData);


    }

    interface Model {

    }
}
