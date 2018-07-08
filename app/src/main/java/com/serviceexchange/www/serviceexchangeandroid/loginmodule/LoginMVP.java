package com.serviceexchange.www.serviceexchangeandroid.loginmodule;

import android.app.Activity;
import android.content.Intent;

import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;
import com.serviceexchange.www.serviceexchangeandroid.utils.BaseView;

public interface LoginMVP {

    public interface FacebookLoginModel {

        void registerCallBackForFaceLogin();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public interface  LinkedInLoginModel{
        interface OnLoginFinishedListener {

            void onSuccess(String pictureUrl,String  firstName ,String lastName, String emailAddress );

        }
        void logout();
        void response( int requestCode, int resultCode, Intent data);
        void login( OnLoginFinishedListener onLoginFinishedListener);

        void setLogInPresenter(Presenter logInPresenter);
    }

    public interface View extends BaseView {

        Activity getActivity();
        void logInChang(String pictureUrl, String firstName , String lastName, String emailAddress );
        void skipButtonClicked();
        void skipToGuestButtonClicked();
        void loginSuccess();
        void hideProgressBar();
        void showProgressBar();
    }

    public interface Presenter extends BasePresenter {

        void setView(View view);
        void registerCallBackForFaceLogin();
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void handleLogin();
        void handleResponse(int requestCode, int resultCode, Intent data);
        void onDestroy();
        Activity getActivity();
        void done();
        void wating();
        void goToNavigationActivity();
        void goToGuestNavigationActivity();

    }
}
