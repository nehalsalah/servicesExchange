package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled;

import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

import java.util.List;

public interface CancelledMVP {
    interface View {

        void setList(List<TransactionDto> object);

        void showPendingTransactionActivity(TransactionDto trans);
    }

    interface Presenter extends BasePresenter {
        void setView(View view);

        void loadPendingServices(int i);

        void cardOnClickAction(TransactionDto trans);
    }
}
