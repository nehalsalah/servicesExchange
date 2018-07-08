package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;

import java.util.List;

public interface ServiceDetailMVP {

    interface View {

        void setReviewsList(List<ReviewDTO> object);

        void orderSuccess();

        void orderFailed();
    }

    interface Presenter {
        void setView(View view);
        void reRegister();
        void terminate();
        void sendOrder(ServiceDTO service, UserDTO user);
    }
}
