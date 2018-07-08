package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity;

import android.net.Uri;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

public interface TimelineMVP {
    interface View {

        void showUploadAndSubmitDialogue();

        void goToChatBox();

        void startFileChooser();

        void setFileUrl(String object);

        void submitSuccess();
    }

    interface Presenter {
        void setView(View view);
        void reRegister();
        void terminate();

        void deliverNowAction();

        void chatAction();

        void uploadButtonAction();

        void uploadFile(Uri uri);

        void submitButtonAction(TransactionDto trans);
    }
}
