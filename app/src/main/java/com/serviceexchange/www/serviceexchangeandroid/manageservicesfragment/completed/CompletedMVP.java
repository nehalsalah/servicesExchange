package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed;

import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

import java.util.List;

public interface CompletedMVP {
    interface View {

        void setList(List<TransactionDto> object);
    }

    interface Presenter extends BasePresenter {
        void setView(View view);

        void loadCompletedServices(int page);
    }
}