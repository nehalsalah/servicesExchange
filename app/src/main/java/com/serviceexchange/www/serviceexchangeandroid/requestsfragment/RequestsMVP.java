package com.serviceexchange.www.serviceexchangeandroid.requestsfragment;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

import java.util.List;

public interface RequestsMVP {
    interface View {

        void showOfferDetailsFragment(ServiceDTO service);

        void setList(List<ServiceDTO> object);
    }

    interface Presenter extends BasePresenter {
        void setView(View view);

        void offerButtonClickAction(ServiceDTO service);

        void loadAllRequests(int page);
    }
}
