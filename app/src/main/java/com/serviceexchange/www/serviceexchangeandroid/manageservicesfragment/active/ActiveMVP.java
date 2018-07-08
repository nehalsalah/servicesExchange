package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active;

import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

import java.util.List;

public interface ActiveMVP {
    interface View {

        void showServiceDetailsView(TransactionDto trans);

        void setList(List<TransactionDto> object);
    }

    interface Presenter extends BasePresenter {

        void setView(View view);

        void cardClickedAtPosition(TransactionDto trans);

        void loadActiveServices(int i);
    }
}
