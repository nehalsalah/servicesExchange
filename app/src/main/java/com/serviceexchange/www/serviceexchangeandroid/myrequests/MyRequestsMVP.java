package com.serviceexchange.www.serviceexchangeandroid.myrequests;


import com.service_exchange.api_services.dao.dto.ServiceDTO;

import java.util.List;

public interface MyRequestsMVP {

    interface View {
        void goToWhoRequest();
        void setServiceList(List<ServiceDTO> serviceDTOList);

    }

    interface Presenter {
        void setView(View view);

        void onCardListItemClicked();

        void loadAllMyRequests(int page);

        void register();

        void terminate();
    }

    interface Model {

    }
}
