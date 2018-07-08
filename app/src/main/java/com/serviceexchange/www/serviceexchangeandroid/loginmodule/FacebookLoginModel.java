package com.serviceexchange.www.serviceexchangeandroid.loginmodule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class FacebookLoginModel implements LoginMVP.FacebookLoginModel {

    public static String TAG = "FacebookLoginModel";
    private CallbackManager manager;

    public FacebookLoginModel() {
        this.manager = CallbackManager.Factory.create();
    }

    @Override
    public void registerCallBackForFaceLogin() {
        LoginManager.getInstance().registerCallback(manager, new FaceLoginCallback());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        manager.onActivityResult(requestCode, resultCode, data);
        Log.i("hello", "request: " + requestCode + " result: " + resultCode);
    }

    private class FaceLoginCallback implements FacebookCallback<LoginResult> {

        @Override
        public void onSuccess(LoginResult loginResult) {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    JSONObject json = response.getJSONObject();
                    try {
                        if (json != null) {
                            UserDTO user = new UserDTO();
                            user.setAccountId(json.getString("id"));
                            user.setImage(json.getJSONObject("picture").getJSONObject("data").getString("url"));
                            user.setName(json.getString("name"));
                            if (json.has("email"))
                                user.setUserEmailCollection(Arrays.asList(json.getString("email")));
                            else
                                user.setUserEmailCollection(Arrays.asList("dummy@gmail.com"));
                            user.setAccountType("faceBook");
                            EventBus.getDefault().post(new CustomEvent(CustomEventType.FACEBOOK_USER, user));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link,email,picture");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            Log.d(TAG, error.toString());
        }
    }
}
