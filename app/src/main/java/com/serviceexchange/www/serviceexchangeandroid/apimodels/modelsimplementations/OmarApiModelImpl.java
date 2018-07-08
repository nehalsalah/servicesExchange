package com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsimplementations;

import com.service_exchange.api_services.dao.dto.ReviewDTO;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.CategoryApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.MyRequestsApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.ProfileApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.ServiceApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.retrofit.TransactionApiInterface;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks.MainCategoriesCallback;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks.OfferedServicesForCategoryCallback;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks.RequestedServicesForCategoryCallback;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks.SubCategoriesForCategoryCallback;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.retrofitcallbacks.TopCategoriesCallback;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks.FirebaseTokenCallback;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks.ServerLoginRegisterCallback;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.retrofitcallbacks.ServerTokenCallback;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks.ActiveServicesCallback;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks.ApprovedTransactionCallback;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks.CompleteTransactionCallback;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks.PendingServicesCallback;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.retrofitcallbacks.SubmitTransactionCallback;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.retrofitcallbacks.UserAcceptedThenAcceptTransactionCallback;
import com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.retrofitCallbacks.CancelTransCallback;
import com.serviceexchange.www.serviceexchangeandroid.offerRecevidServices.retrofitCallbacks.OrdersCallback;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserAuth;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.retrofitcallbacks.RequestsCallback;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.retrofitcallbacks.StartTransactionCallback;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class OmarApiModelImpl implements OmarApiModelInt {

    Retrofit retrofit;

    public OmarApiModelImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    @Override
    public void loginOrRegister(UserDTO user) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface.logInOrSignup(user).enqueue(new ServerLoginRegisterCallback());
    }

    @Override
    public void loadMainCategories() {
        CategoryApiInterface apiInterface = retrofit.create(CategoryApiInterface.class);
        apiInterface.getMainCategories().enqueue(new MainCategoriesCallback());
    }

    @Override
    public void loadTopCategories(int length) {
        CategoryApiInterface apiInterface = retrofit.create(CategoryApiInterface.class);
        apiInterface.getTopCategories(length, null).enqueue(new TopCategoriesCallback());
    }

    @Override
    public void loadOfferedServicesForCategory(int categoryId, int page) {
        CategoryApiInterface apiInterface = retrofit.create(CategoryApiInterface.class);
        apiInterface.getServicesForCategories(categoryId, "offerd", page, null).enqueue(new OfferedServicesForCategoryCallback());
    }

    @Override
    public void loadRequestedServicesForCategory(int categoryId) {
        CategoryApiInterface apiInterface = retrofit.create(CategoryApiInterface.class);
        apiInterface.getServicesForCategories(categoryId, "requested", 0, null).enqueue(new RequestedServicesForCategoryCallback());
    }

    @Override
    public void loadSubCategoriesForCategory(int categoryId) {
        CategoryApiInterface apiInterface = retrofit.create(CategoryApiInterface.class);
        apiInterface.getSubCategoriesForCategory(categoryId, null).enqueue(new SubCategoriesForCategoryCallback());
    }

    @Override
    public void loadActiveServices(UserDTO user, int page) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.loadActiveServicesForUer(user.getId(), page, null).enqueue(new ActiveServicesCallback());
    }

    @Override
    public void submitTransaction(TransactionDto trans) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.submitTransactionForConfirmation(trans,  null).enqueue(new SubmitTransactionCallback());
    }

    @Override
    public void loadPendingTransactions(UserDTO user, int page) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.loadPendingTransactionsForUser(user.getId(), page, null).enqueue(new PendingServicesCallback());
    }

    @Override
    public void loadCompletedServices(UserDTO user, int page) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.loadCompletedTransactionsForUser(user.getId(), page, null).enqueue(new CompleteTransactionCallback());
    }

    @Override
    public void loadAllRequests(int page) {
        ServiceApiInterface apiInterface = retrofit.create(ServiceApiInterface.class);
        apiInterface.loadAllRequests(page).enqueue(new RequestsCallback());
    }

    @Override
    public void sendOffer(TransactionDto trans) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.sendOfferOnService(trans, null).enqueue(new StartTransactionCallback());
    }

    @Override
    public void getTokenFromServer(UserDTO user) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        apiInterface
                .getServerToken(new UserAuth(user.getUserEmailCollection().get(0), user.getAccountId()))
                .enqueue(new ServerTokenCallback());
    }

    @Override
    public void sendFirebaseTokenToServer(UserDTO user, String refreshedToken) {
        ProfileApiInterface apiInterface = retrofit.create(ProfileApiInterface.class);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("firebaseId", refreshedToken);
        map.put("type", "android");
        apiInterface.refreshFirebaseToken(map).enqueue(new FirebaseTokenCallback());
    }

    @Override
    public void approveTransaction(TransactionDto trans, ReviewDTO review) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        Map<String, Object> map = new HashMap<>();
        map.put("transaction", trans);
        map.put("review", review);
        apiInterface.approveTransaction(map).enqueue(new ApprovedTransactionCallback());
    }

    @Override
    public void cancelOffer(UserDTO user, TransactionDto trans) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.rejectTransaction(trans).enqueue(new CancelTransCallback());
    }

    @Override
    public void acceptOffer(UserDTO user, TransactionDto trans) {
        MyRequestsApiInterface apiInterface = retrofit.create(MyRequestsApiInterface.class);
        apiInterface.userAcceptedThenAcceptTransaction(trans).enqueue(new UserAcceptedThenAcceptTransactionCallback());
    }

    @Override
    public void loadOrders(UserDTO user) {
        TransactionApiInterface apiInterface = retrofit.create(TransactionApiInterface.class);
        apiInterface.loadOrders(user.getId()).enqueue(new OrdersCallback());
    }
}
