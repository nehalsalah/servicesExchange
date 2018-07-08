package com.serviceexchange.www.serviceexchangeandroid.loginmodule;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.facebook.Profile;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Token;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.Arrays;

public class LoginPresenter implements LoginMVP.Presenter, LoginMVP.LinkedInLoginModel.OnLoginFinishedListener {

    private LoginMVP.View view;
    private LoginMVP.FacebookLoginModel facebookLoginModel;
    private LoginMVP.LinkedInLoginModel linkedInLoginModel;
    private OmarApiModelInt apiModel;
    private SharedPreferencesModelInt sharedPreferencesModel;

    private static final String TAG = "LOGIN";

    public LoginPresenter(LoginMVP.FacebookLoginModel facebookLoginModel
            , LoginMVP.LinkedInLoginModel linkedInLoginModel
            , OmarApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModel) {
        this.facebookLoginModel = facebookLoginModel;
        this.linkedInLoginModel = linkedInLoginModel;
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        linkedInLoginModel.setLogInPresenter(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(LoginMVP.View view) {
        this.view = view;
    }

    // Omar is here ////////////////////////////////////////////////////////////////////////////////

    @Override
    public void reRegister() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    @Override
    public void onPostEvent(CustomEvent event) {
        if (event.getEventType() == CustomEventType.FACEBOOK_USER
                || event.getEventType() == CustomEventType.LINKEDIN_USER) {
            UserDTO user = (UserDTO) event.getObject();
            if (event.getEventType() == CustomEventType.FACEBOOK_USER){
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    Uri uri = profile.getProfilePictureUri(500, 500);
                    String s = uri.toString();
                    user.setImage(s);
                }
            }
            user.setStatus("onLine");
            Log.i(TAG + "social",""+user.getId());
            apiModel.loginOrRegister((UserDTO) event.getObject());
        } else if (event.getEventType() == CustomEventType.USER) {
            UserDTO user = (UserDTO) event.getObject();
            Log.i(TAG + "user",""+user.getId());
            sharedPreferencesModel.putUserInSharedPreferences(user);
            apiModel.getTokenFromServer(user);
            String firebaseToken  = FirebaseInstanceId.getInstance().getToken();
            Log.i(TAG + "firebaseToken", firebaseToken);
            apiModel.sendFirebaseTokenToServer(user, firebaseToken);
        } else if (event.getEventType() == CustomEventType.SERVER_TOKEN) {
            Token token = (Token) event.getObject();
            String tokenString = ":" + token.getToken();
            sharedPreferencesModel.putToken(tokenString);
            Log.i(TAG + "token", sharedPreferencesModel.getToken());
            view.loginSuccess();
        } else if (event.getEventType() == CustomEventType.FAILED_REQUEST) {
            Log.i(TAG, "null");
            view.showAlert("error!");
        } else if (event.getEventType() == CustomEventType.NULL_RESPONSE_BODY) {
            Log.i(TAG, "fail");
            Throwable t = (Throwable) event.getObject();
            view.showAlert("network error!");
        }
    }

    @Override
    public void registerCallBackForFaceLogin() {
        facebookLoginModel.registerCallBackForFaceLogin();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        facebookLoginModel.onActivityResult(requestCode, resultCode, data);
    }

    // Nehal is here ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void handleLogin() {
        linkedInLoginModel.login(this);
    }

    @Override
    public void handleResponse(int requestCode, int resultCode, Intent data) {
        linkedInLoginModel.response(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public Activity getActivity() {
        return view.getActivity();
    }

    @Override
    public void done() {
        view.hideProgressBar();
    }

    @Override
    public void wating() {
        view.showProgressBar();
    }

    @Override
    public void goToNavigationActivity() {
        if (view != null) {
            view.skipButtonClicked();
        }
    }

    @Override
    public void goToGuestNavigationActivity() {
        if (view != null) {
            view.skipToGuestButtonClicked();
        }
    }

    @Override
    public void onSuccess(String pictureUrl, String firstName, String lastName, String emailAddress) {
        if (view != null)
            view.logInChang(pictureUrl, firstName, lastName, emailAddress);

    }
}

