package com.serviceexchange.www.serviceexchangeandroid.loginmodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.facebook.login.widget.LoginButton;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.guestnavigationdrawer.GuestNavigationDrawerActivity;
import com.serviceexchange.www.serviceexchangeandroid.navigationdrawer.NavigationDrawerActivity;
import com.serviceexchange.www.serviceexchangeandroid.utils.Alerter;
import com.serviceexchange.www.serviceexchangeandroid.utils.HashKeyGenerator;

import java.util.Arrays;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View, View.OnClickListener {

    private static final String TAG = "myTag";

    boolean flag = false;
    @Inject
    LoginMVP.Presenter presenter;
    @Inject
    Context context;
    private ProgressBar progressBar;

    private Button home;
    private Button skip;
    private CardView faceLoginCard;
    private CardView linkedInCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new HashKeyGenerator().printHashKey(getApplicationContext());
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);

        presenter.registerCallBackForFaceLogin();

        faceLoginCard = findViewById(R.id.faceLoginCard);
        faceLoginCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton loginButton = new LoginButton(LoginActivity.this);
                loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
                loginButton.performClick();
                showProgressBar();
            }
        });

        skip = findViewById(R.id.skipButton);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToGuestNavigationActivity();
            }
        });
        initializeControls();

    }

    private void initializeControls() {
        linkedInCard = findViewById(R.id.linkedInLoginCard);
          linkedInCard.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.reRegister();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (flag) {
            presenter.handleResponse(requestCode, resultCode, data);
        } else {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linkedInLoginCard:
                flag = true;
                presenter.handleLogin();
                progressBar.setVisibility(View.VISIBLE);
                break;
       }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void logInChang(String pictureUrl, String firstName, String lastName, String emailAddress) {
        StringBuilder sb = new StringBuilder();
        sb.append("First Name: " + firstName);
        sb.append("\n\n");
        sb.append("Last Name: " + lastName);
        sb.append("\n\n");
        sb.append("Email: " + emailAddress);
        Log.i("linkedInData", sb.toString());
    }

    @Override
    public void skipButtonClicked() {
        Intent goToNavigationIntent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(goToNavigationIntent);
    }

    @Override
    public void skipToGuestButtonClicked() {
        Intent goToNavigationIntent = new Intent(this, GuestNavigationDrawerActivity.class);
        startActivity(goToNavigationIntent);
        finish();
    }

    @Override
    public void loginSuccess() {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onRequestFail() {

    }

    @Override
    public void startLoadingView() {
        
    }

    @Override
    public void stopLoadingView() {

    }

    @Override
    public void showAlert(String message) {
//        Alerter.showAlert(getActivity(), message);
    }
}
