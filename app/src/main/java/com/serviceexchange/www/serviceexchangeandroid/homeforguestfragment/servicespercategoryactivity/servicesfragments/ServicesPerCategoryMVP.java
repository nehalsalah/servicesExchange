package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

import java.util.List;

public interface ServicesPerCategoryMVP {

    interface View {

        void setOfferedServicesList(List<ServiceDTO> object);

        void setRequestedServicesList(List<ServiceDTO> object);

        void startServiceDetailFragment(ServiceDTO service);
    }

    interface Presenter extends BasePresenter {
        void setView(View view);

        void loadRequestedServicesList(int categoryId);

        void loadOfferedServicesList(int categoryId, int i);

        void serviceCardOnClickAction(ServiceDTO service);
    }

}
