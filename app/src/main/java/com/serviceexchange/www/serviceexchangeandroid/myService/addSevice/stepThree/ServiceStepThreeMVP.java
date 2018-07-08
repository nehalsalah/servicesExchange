package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.stepThree;

import android.net.Uri;

public interface ServiceStepThreeMVP {
    interface View {

        void addService();

        void  setFileUrl(String fileUrl);
    }

    interface Presenter {

        void setView(ServiceStepThreeMVP.View view);
        void register();

        void terminate();
        void uploadFile(Uri uri);


    }
    interface Model {

    }
}
