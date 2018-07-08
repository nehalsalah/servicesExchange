package com.serviceexchange.www.serviceexchangeandroid.myrequests.allrequestonservice;

import com.service_exchange.api_services.dao.dto.TransactionEslam;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

public interface AllRequestOnServiceMVP {

    interface View {

        void acceptOffer(TransactionDto transactionDto);

        void setTransactionList(List<TransactionEslam> transactionEslams);

    }

    interface Presenter {

        void setView(View view);

        void onAcceptOfferClicked(TransactionDto transactionDto);

        void loadAllRequstOnService(int serviceId);

        void register();

        void terminate();
    }

    interface Model {

    }
}
