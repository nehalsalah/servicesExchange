package com.serviceexchange.www.serviceexchangeandroid.utils;

public interface BaseView {
    void onRequestFail();
    void startLoadingView();
    void stopLoadingView();
    void showAlert(String message);
}
