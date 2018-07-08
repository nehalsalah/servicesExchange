package com.serviceexchange.www.serviceexchangeandroid.myService.displayService;

import com.serviceexchange.www.serviceexchangeandroid.earning.Pojo;

import java.util.ArrayList;

public interface DisplayServiceMVP {
    interface View {


    }

    interface Presenter {

        ArrayList<Pojo> getData();

    }
    interface Model {

    }
}
