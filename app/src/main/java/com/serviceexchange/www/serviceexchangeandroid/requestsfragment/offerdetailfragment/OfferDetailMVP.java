package com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

public interface OfferDetailMVP {

    interface View {
        ServiceDTO getService();
        String getPointsText();
        String getDurationText();
        String getDescText();

        void transStartedSuccess();

        void cantSendOffer();
    }

    interface Presenter extends BasePresenter {
        void setView(View view);

        void sendOfferButtonAction();

    }
}
