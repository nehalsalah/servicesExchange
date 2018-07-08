package com.serviceexchange.www.serviceexchangeandroid.profile;

import android.net.Uri;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.homeStatistic.StatisticMVP;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.LoginMVP;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;

public interface ProfileMVP {

    interface View {
        void setData(UserStatics data);
        void  setUser(UserDTO user);

        void setFileUrl(String object);
    }

    interface Presenter {
        void setView(View view);
        void onDestroy();
        void register();
        void  setUserData();

        void uploadFile(Uri uri);

        void terminate();
        void loadUserStatics();
        void editDate(String bio,String name,String desc,String img,String add);
    }
    interface Model {

    }
}
