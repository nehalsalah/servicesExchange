package com.serviceexchange.www.serviceexchangeandroid.application;

import android.app.Application;

import com.serviceexchange.www.serviceexchangeandroid.application.dagger.AppComponent;
import com.serviceexchange.www.serviceexchangeandroid.application.dagger.AppModule;
import com.serviceexchange.www.serviceexchangeandroid.application.dagger.DaggerAppComponent;
import com.serviceexchange.www.serviceexchangeandroid.dagger.MainModule;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.dagger.LoginModule;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;

public class App extends Application {

    private AppComponent component;
    private SharedPreferencesModelInt preferencesModel;

    public AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .loginModule(new LoginModule())
                .mainModule(new MainModule())
                .build();
        preferencesModel = SharedPreferencesModel.getInstance(getApplicationContext());
        preferencesModel.putApplicationRunningFlag(true);
    }

    @Override
    public void onTerminate() {
        preferencesModel.putApplicationRunningFlag(false);
        super.onTerminate();
    }
}
