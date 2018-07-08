package com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

public interface EslamApiModelInt {

    void getAllUserTransactionChats(int userId, int pageNum);

    void getAllTransactionMessages(int transactionId, int userId, int pageNum);

    void sendTransactionMessage(MessageGeneralDto messageGeneralDto);

    void getReq(int userId, String type);

    void addReq(ServiceDTO serviceDTO);

    void getAllRequstOnService(int serviceId);

    void userAcceptedThenAcceptTransaction(TransactionDto transactionDto);
}
