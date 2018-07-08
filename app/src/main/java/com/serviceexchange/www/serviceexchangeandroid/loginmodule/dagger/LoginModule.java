package com.serviceexchange.www.serviceexchangeandroid.loginmodule.dagger;

import android.content.Context;

import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.FacebookLoginModel;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LinkedInLogInModelImp;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginMVP;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginPresenter;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginMVP.Presenter providePresenter(LoginMVP.FacebookLoginModel facebookLoginModel
            , LoginMVP.LinkedInLoginModel linkedInLoginModel
            , OmarApiModelInt apiModel
            , SharedPreferencesModelInt sharedPreferencesModel) {
        return new LoginPresenter(facebookLoginModel,linkedInLoginModel, apiModel, sharedPreferencesModel);
    }

    @Provides
    LoginMVP.FacebookLoginModel provideModel() {
        return new FacebookLoginModel();
    }

    @Provides
    LoginMVP.LinkedInLoginModel provideLiModel(Context context) {
        return new LinkedInLogInModelImp(context);
    }

}
