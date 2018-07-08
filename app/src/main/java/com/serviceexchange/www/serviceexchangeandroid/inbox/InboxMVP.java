package com.serviceexchange.www.serviceexchangeandroid.inbox;

import com.service_exchange.api_services.dao.dto.TransactionChatDto;

import java.util.List;

public interface InboxMVP {
    interface View {
        void goToInboxDetailActivity(TransactionChatDto transactionChatDto);

        void colorActionButton();

        void unColorActionButton();

        void setMessageList(List<TransactionChatDto> transactionChatDtoList);
    }

    interface Presenter {
        void setView(View view);

        void onCardListItemClicked(TransactionChatDto transactionChatDto);

        void onActionListItemClicked();

        void loadAllUserTransactionChats(int userId, int pageNum);

        void register();

        void terminate();
    }

    interface Model {

    }
}
