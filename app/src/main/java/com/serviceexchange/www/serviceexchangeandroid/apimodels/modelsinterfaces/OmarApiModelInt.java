package com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

public interface OmarApiModelInt {

    void loginOrRegister(UserDTO user);

    void loadMainCategories();

    void loadTopCategories(int length);

    void loadOfferedServicesForCategory(int categoryId, int page);

    void loadRequestedServicesForCategory(int categoryId);

    void loadSubCategoriesForCategory(int categoryId);

    void loadActiveServices(UserDTO user, int page);

    void submitTransaction(TransactionDto trans);

    void loadPendingTransactions(UserDTO user, int page);

    void loadCompletedServices(UserDTO user, int page);

    void loadAllRequests(int page);

    void sendOffer(TransactionDto trans);

    void getTokenFromServer(UserDTO user);

    void sendFirebaseTokenToServer(UserDTO user, String refreshedToken);

    void approveTransaction(TransactionDto trans, ReviewDTO review);

    void cancelOffer(UserDTO user, TransactionDto trans);

    void acceptOffer(UserDTO user, TransactionDto trans);

    void loadOrders(UserDTO user);
}
