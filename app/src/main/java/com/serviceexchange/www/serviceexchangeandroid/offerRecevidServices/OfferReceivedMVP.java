package com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices;

import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

public interface OfferReceivedMVP {

    interface View {

        void setOfferReceivedList(List<TransactionDto> object);

        void reload();
    }

     interface Presenter {
         void register();

         void terminate();

         void setView(OfferReceivedMVP.View view);
         void loadOfferReceivedList();

         void acceptOfferClicked(TransactionDto trans);

         void cancelOfferClicked(TransactionDto trans);
     }
    interface Model {

    }

}

