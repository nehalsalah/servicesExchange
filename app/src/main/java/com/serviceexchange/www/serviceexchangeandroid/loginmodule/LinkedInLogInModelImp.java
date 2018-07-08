package com.serviceexchange.www.serviceexchangeandroid.loginmodule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LinkedInLogInModelImp implements LoginMVP.LinkedInLoginModel {

    Context context;
    LoginMVP.Presenter logInPresenter;
    public LinkedInLogInModelImp(Context context) {
        this.context = context;
    }


    @Override
    public void logout() {
        LISessionManager.getInstance(context).clearSession();
    }


    @Override
    public void response(int requestCode, int resultCode, Intent data) {
        // Add this line to your existing @Override onActivityResult() method
        LISessionManager.getInstance(context).onActivityResult(logInPresenter.getActivity(), requestCode, resultCode, data);
    }

    @Override
    public void login(  final OnLoginFinishedListener onLoginFinishedListener) {
        logInPresenter.wating();
        LISessionManager.getInstance(context).init(logInPresenter.getActivity(), buildScope(), new AuthListener() {
            @Override
            public void onAuthSuccess() {
                // Authentication was successful.  You can now do
                // other calls with the SDK.
                fetchPersonalInfo(context,onLoginFinishedListener);
            }

            @Override
            public void onAuthError(LIAuthError error) {
                // Handle authentication errors
                logInPresenter.done();
               // showDialog("you should download linkedIn app");
                Log.e("authentication errors",error.toString());
            }
        }, true);

    }

    @Override
    public void setLogInPresenter(LoginMVP.Presenter logInPresenter) {
        this.logInPresenter= logInPresenter;
    }


    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.W_SHARE, Scope.R_EMAILADDRESS);
    }

    private void fetchPersonalInfo(Context context, final OnLoginFinishedListener onLoginFinishedListener ){

        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name,public-profile-url,picture-url,email-address,picture-urls::(original))";

        APIHelper apiHelper = APIHelper.getInstance(context);
        apiHelper.getRequest(context, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                // Success!
                try {
                    JSONObject jsonObject = apiResponse.getResponseDataAsJson();
                    UserDTO user = new UserDTO();
                    user.setAccountId(jsonObject.getString("id"));
                    user.setImage(jsonObject.getString("pictureUrl"));
                    user.setName(jsonObject.getString("firstName")+" "+ jsonObject.getString("lastName"));
                    user.setUserEmailCollection(Arrays.asList(jsonObject.getString("emailAddress")));
                    user.setAccountType("linkedIn");
                    user.setBalance(25);//...!!!
                    EventBus.getDefault().post(new CustomEvent(CustomEventType.LINKEDIN_USER, user));

                } catch (JSONException e) {
                    e.printStackTrace();
                    logInPresenter.done();
                    showDialog("there is an error with internet connection \n Try Again");
                }

            }

            @Override
            public void onApiError(LIApiError liApiError) {
                // Error making GET request!
                Log.e("NIKHIL",liApiError.getMessage());
                logInPresenter.done();
                showDialog("there is an error with internet connection \n Try Again");
            }
        });

    }
    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext(),AlertDialog.THEME_HOLO_LIGHT);
        builder.setMessage(msg);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                logInPresenter.handleLogin();
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
