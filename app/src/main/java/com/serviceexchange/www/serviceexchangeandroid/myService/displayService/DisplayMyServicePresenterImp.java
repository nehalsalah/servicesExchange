package com.serviceexchange.www.serviceexchangeandroid.myService.displayService;

import com.serviceexchange.www.serviceexchangeandroid.earning.Pojo;

import java.util.ArrayList;

public class DisplayMyServicePresenterImp implements DisplayServiceMVP.Presenter {

    @Override
    public ArrayList<Pojo> getData() {
        ArrayList<Pojo> data;
        data = new ArrayList<>();
        Pojo pojo=new Pojo("service name 1","points 1", 1);
        Pojo pojo2=new Pojo("service name 2","points 2", 5);
        Pojo pojo3=new Pojo("service name 3","points 3", 3);
        data.add(pojo);
        data.add(pojo2);
        data.add(pojo3);
        return data;
    }
}

