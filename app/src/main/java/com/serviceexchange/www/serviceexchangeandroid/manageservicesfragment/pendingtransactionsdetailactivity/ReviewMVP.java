package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.pendingtransactionsdetailactivity;

import android.content.Context;

import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

public interface ReviewMVP {

    interface View {
        String getRatingString();
        String getCommentString();

        void showInvalidInputMessage();
        TransactionDto getTrans();

        void submissionSuccess();
    }

    interface Presenter extends BasePresenter {
        void setView(View view);

        void downloadButtonAction(String jopFile);

        void submitButtonAction();
    }
}
