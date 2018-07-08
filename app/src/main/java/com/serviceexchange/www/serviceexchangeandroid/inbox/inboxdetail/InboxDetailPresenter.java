package com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail;

import android.support.annotation.Nullable;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class InboxDetailPresenter implements InboxDetailMVP.Presenter {

    @Nullable
    private InboxDetailMVP.View view;
    private EslamApiModelInt apiModelInt;
    private SharedPreferencesModelInt preferencesModel;
    private UserDTO user;

    public InboxDetailPresenter(EslamApiModelInt apiModelInt, SharedPreferencesModelInt preferencesModel) {
        this.apiModelInt = apiModelInt;
        this.preferencesModel = preferencesModel;
        user = preferencesModel.getCurrentUserFromSharedPreferences();
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(InboxDetailMVP.View view) {
        this.view = view;
    }

    @Override
    public void onAttchmentButtonClicked() {

    }

    @Override
    public void onSendButtonClicked(MessageGeneralDto messageGeneralDto) {
        view.sendMessage(messageGeneralDto);
    }

    @Override
    public void loadAllTransactionMessages(int transactionId, int pageNum) {
        apiModelInt.getAllTransactionMessages(transactionId, user.getId(), pageNum);
    }

    @Override
    public void sendTransactionMessages(MessageGeneralDto messageGeneralDto) {
        apiModelInt.sendTransactionMessage(messageGeneralDto);
    }

    @Override
    public void register() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setIsChatRunningFlag(boolean flag) {
        preferencesModel.putChatRunningFlag(flag);
    }


    @Subscribe
    public void MessageListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.ALL_TRANSACTION_MESSAGES)
            view.setMessageList((List<MessageGeneralDto>) event.getObject());
        else if (event.getEventType() == CustomEventType.NOTIFICATION_MESSAGE)
            view.addMessageToList((MessageGeneralDto) event.getObject());
        else if (event.getEventType() == CustomEventType.SEND_TRANSACTION_MESSAGES)
            view.addMessageToList((MessageGeneralDto) event.getObject());
    }
}
