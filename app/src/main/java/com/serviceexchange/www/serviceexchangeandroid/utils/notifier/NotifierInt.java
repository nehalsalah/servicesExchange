package com.serviceexchange.www.serviceexchangeandroid.utils.notifier;

public interface NotifierInt {

    void handleMessage(String messageString, String sender);

    void handleTransaction(String s);
}
