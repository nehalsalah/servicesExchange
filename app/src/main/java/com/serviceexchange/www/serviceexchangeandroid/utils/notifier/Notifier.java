package com.serviceexchange.www.serviceexchangeandroid.utils.notifier;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;
import com.service_exchange.api_services.dao.dto.MessageGeneralDto;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.inbox.inboxdetail.InboxDetailActivity;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;

public class Notifier implements NotifierInt {
    private Context aContext;
    private SharedPreferencesModelInt preferencesModel;

    public Notifier(Context context, SharedPreferencesModelInt preferencesModel) {
        this.aContext = context;
        this.preferencesModel = preferencesModel;
    }

    @Override
    public void handleMessage(String messageString, String sender) {
        Gson gson = new Gson();
        MessageGeneralDto message = gson.fromJson(messageString, MessageGeneralDto.class);
        makeMessageNotification(message, sender);
        if (preferencesModel.isChatRunning()) {
//            EventBus.getDefault().post(new CustomEvent(CustomEventType.NOTIFICATION_MESSAGE, message));
        }
    }

    private void makeMessageNotification(MessageGeneralDto message, String sender) {
        if (message != null) {
            NotificationManager notificationManager = (NotificationManager) aContext.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pIntent = getContentIntentForChatActivity(message.getTransactionId());
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(aContext)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(sender)
                    .setContentText(message.getText())
                    .setContentIntent(pIntent);
            notificationManager.notify(450, mBuilder.build());
        }
    }

    private PendingIntent getContentIntentForChatActivity(Integer transactionId) {
        Intent intent = new Intent(aContext, InboxDetailActivity.class);
        intent.putExtra("transactionId", transactionId);
        PendingIntent pIntent = PendingIntent.getActivity(aContext, 451, intent, 0);
        return pIntent;
    }

    @Override
    public void handleTransaction(String transactionString) {
        Gson gson = new Gson();
        TransactionDto trans = gson.fromJson(transactionString, TransactionDto.class);
        makeTransactionNotification();
    }

    private void makeTransactionNotification() {
    }
}
