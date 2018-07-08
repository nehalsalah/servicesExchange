package com.serviceexchange.www.serviceexchangeandroid.firebasenotifications;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;

import javax.inject.Inject;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Inject
    OmarApiModelInt apiModel;
    @Inject
    SharedPreferencesModelInt preferencesModel;
    @Inject
    Context aContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        UserDTO user = preferencesModel.getCurrentUserFromSharedPreferences();
        if (user != null) {
            apiModel.sendFirebaseTokenToServer(user, refreshedToken);
        }
        Log.i("firebase token", refreshedToken);
    }
}
