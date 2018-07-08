package com.serviceexchange.www.serviceexchangeandroid.splashactivity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.demoinstruction.DemoInstructionActivity;
import com.serviceexchange.www.serviceexchangeandroid.guestnavigationdrawer.GuestNavigationDrawerActivity;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginActivity;
import com.serviceexchange.www.serviceexchangeandroid.navigationdrawer.NavigationDrawerActivity;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.FirstEnterFlag;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.utils.HashKeyGenerator;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferencesModel sharedPreferencesModel;
    FirstEnterFlag firstEnterFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        sharedPreferencesModel = SharedPreferencesModel.getInstance(this);
        firstEnterFlag = FirstEnterFlag.getInstance(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firstEnterFlag.getFirstEnterFlag() == 0) {
                    Intent intentLoginActivity = new Intent(SplashActivity.this, DemoInstructionActivity.class);
                    startActivity(intentLoginActivity);
                    finish();
                } else if (sharedPreferencesModel.getCurrentUserFromSharedPreferences() == null) {
                    Intent intentLoginActivity = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intentLoginActivity);
                    finish();
                } else {
                    UserDTO userDTO = sharedPreferencesModel.getCurrentUserFromSharedPreferences();
                    Log.v("UserName", userDTO.getName());
                    Intent intentLoginActivity = new Intent(SplashActivity.this, NavigationDrawerActivity.class);
                    startActivity(intentLoginActivity);
                    finish();
                }
            }
        }, 1000);

    }
}

