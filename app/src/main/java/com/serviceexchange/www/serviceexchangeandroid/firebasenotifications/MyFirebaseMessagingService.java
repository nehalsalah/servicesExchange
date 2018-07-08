package com.serviceexchange.www.serviceexchangeandroid.firebasenotifications;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.notifier.NotifierInt;

import java.util.Map;

import javax.inject.Inject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessaging";

    @Inject
    NotifierInt notifier;
    @Inject
    Context aContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> map = remoteMessage.getData();
        Log.i("noti", map.toString());
        String type = map.get("type");
        switch (type) {
            case "messageType":
                notifier.handleMessage(map.get("message"), map.get("userNameOfMessage"));
                break;

            case "transactionType":
                notifier.handleTransaction(map.get("transaction"));
                break;
        }
    }
}
