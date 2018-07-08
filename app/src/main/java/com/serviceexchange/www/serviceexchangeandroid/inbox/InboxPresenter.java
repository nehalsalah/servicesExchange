package com.serviceexchange.www.serviceexchangeandroid.inbox;

import android.support.annotation.Nullable;

import com.service_exchange.api_services.dao.dto.TransactionChatDto;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class InboxPresenter implements InboxMVP.Presenter {

    @Nullable
    private InboxMVP.View view;
    private EslamApiModelInt apiModelInt;

    public InboxPresenter(EslamApiModelInt apiModelInt) {
        this.apiModelInt = apiModelInt;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(InboxMVP.View view) {
        this.view = view;
    }

    @Override
    public void onCardListItemClicked(TransactionChatDto transactionChatDto) {
        view.goToInboxDetailActivity(transactionChatDto);
    }

    @Override
    public void onActionListItemClicked() {

    }

    @Override
    public void loadAllUserTransactionChats(int userId, int pageNum) {
        apiModelInt.getAllUserTransactionChats(userId, pageNum);
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

    @Subscribe
    public void MessageListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.ALL_USER_TRANSACTION_CHATS)
            view.setMessageList((List<TransactionChatDto>) event.getObject());
    }
}
