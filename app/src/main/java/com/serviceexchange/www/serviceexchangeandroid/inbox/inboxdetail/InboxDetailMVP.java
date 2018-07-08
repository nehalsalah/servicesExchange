package com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.TransactionChatDto;

import java.util.List;

public interface InboxDetailMVP {
    interface View {

        void setMessageList(List<MessageGeneralDto> messageGeneralDtoList);

        void addMessageToList(MessageGeneralDto object);

        void sendMessage(MessageGeneralDto messageGeneralDto);
    }

    interface Presenter {

        void setView(View view);

        void onAttchmentButtonClicked();

        void onSendButtonClicked(MessageGeneralDto messageGeneralDto);

        void loadAllTransactionMessages(int transactionId, int pageNum);

        void sendTransactionMessages(MessageGeneralDto messageGeneralDto);

        void register();

        void terminate();

        void setIsChatRunningFlag(boolean flag);

    }

    interface Model {

    }
}
