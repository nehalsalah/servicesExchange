package com.serviceexchange.www.serviceexchangeandroid.homeStatistic;

import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;

public interface StatisticMVP {
     interface View {

          void setUserStatics(UserStatics object);

    }

     interface Presenter {
         void register();

         void terminate();

         void setView(StatisticMVP.View view);
         void loadUserStatics();

    }
     interface Model {

    }
}
