package com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsimplementations;

import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.EslamApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.MessageApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.MyRequestsApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.retrofitcallbacks.AllTransactionMessagesCallback;
import com.serviceexchange.www.serviceexchangeandroid.inbox.retrofitcallbacks.AllUserTransactionChatsCallback;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.retrofitcallbacks.SendTransactionMessageCallback;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks.AddReqCallback;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks.GetAllRequstOnServiceCallback;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks.GetReqCallback;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks.UserAcceptedThenAcceptTransactionCallback;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import retrofit2.Retrofit;

public class EslamApiModelImpl implements EslamApiModelInt {

    Retrofit retrofit;

    public EslamApiModelImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void getAllUserTransactionChats(int userId, int pageNum) {
        MessageApiInterface messageApiInterface = retrofit.create(MessageApiInterface.class);
        messageApiInterface.getAllUserTransactionChats(userId, pageNum).enqueue(new AllUserTransactionChatsCallback());
    }

    @Override
    public void getAllTransactionMessages(int transactionId, int userId, int pageNum) {
        MessageApiInterface messageApiInterface = retrofit.create(MessageApiInterface.class);
        messageApiInterface.getAllTransactionMessages(transactionId, userId, pageNum).enqueue(new AllTransactionMessagesCallback());
    }

    @Override
    public void sendTransactionMessage(MessageGeneralDto messageGeneralDto) {
        MessageApiInterface messageApiInterface = retrofit.create(MessageApiInterface.class);
        messageApiInterface.sendTransactionMessage(messageGeneralDto).enqueue(new SendTransactionMessageCallback());
    }

    @Override
    public void getReq(int userId, String type) {
        MyRequestsApiInterface myRequestsApiInterface = retrofit.create(MyRequestsApiInterface.class);
        myRequestsApiInterface.getReq(userId, type).enqueue(new GetReqCallback());
    }

    @Override
    public void addReq(ServiceDTO serviceDTO) {
        MyRequestsApiInterface myRequestsApiInterface = retrofit.create(MyRequestsApiInterface.class);
        myRequestsApiInterface.addReq(serviceDTO).enqueue(new AddReqCallback());
    }

    @Override
    public void getAllRequstOnService(int serviceId) {
        MyRequestsApiInterface myRequestsApiInterface = retrofit.create(MyRequestsApiInterface.class);
        myRequestsApiInterface.getAllRequstOnService(serviceId).enqueue(new GetAllRequstOnServiceCallback());
    }

    @Override
    public void userAcceptedThenAcceptTransaction(TransactionDto transactionDto) {
        MyRequestsApiInterface myRequestsApiInterface = retrofit.create(MyRequestsApiInterface.class);
        myRequestsApiInterface.userAcceptedThenAcceptTransaction(transactionDto).enqueue(new UserAcceptedThenAcceptTransactionCallback());
    }
}
