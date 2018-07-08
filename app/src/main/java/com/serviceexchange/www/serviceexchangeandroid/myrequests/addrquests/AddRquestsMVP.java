package com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests;

import android.net.Uri;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.MyRequestsMVP;

import java.util.List;

public interface AddRquestsMVP {
    interface View {

        void setRequestTypeCash();

        void setRequestTypeExchange();

        void attachFile();

        void publishRequest();

        void set_request_name();

        void set_request_description();

        void set_duration_days();

        void serviveObjectBack(ServiceDTO serviceDTO);

        void setFileUrl(String object);
    }

    interface Presenter {
        void setView(AddRquestsMVP.View view);

        void addMyRequests(ServiceDTO serviceDTO);

        void cash_add_request_Clicked();

        void exchange_add_request_Clicked();

        void button_attach_file_Clicked();

        void publish_request_Clicked();

        void register();

        void terminate();

        void uploadFile(Uri selectedImage);
    }

    interface Model {

    }
}
