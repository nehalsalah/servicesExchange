package com.serviceexchange.www.serviceexchangeandroid.earning;

import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.pojos.Earning;
import com.serviceexchange.www.serviceexchangeandroid.pojos.UserStatics;

import java.util.ArrayList;
import java.util.List;

public interface EarningMVP {

    interface View {

        void setEarningList(List<Earning> object);
        void setPoints(UserStatics object);
        void setEmptyText();
    }

     interface Presenter {
         void register();

         void terminate();

         void setView(EarningMVP.View view);
         void loadEarningList();
         void loadPoints();
         void emptyList();

    }
    interface Model {

    }

}

