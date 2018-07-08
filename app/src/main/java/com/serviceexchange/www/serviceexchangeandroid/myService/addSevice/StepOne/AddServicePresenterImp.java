package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne;

import android.content.SharedPreferences;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.NehalApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AddServicePresenterImp implements AddServiceMVP.Presenter {

    OmarApiModelInt apiModel;
    SharedPreferencesModelInt sharedPreferencesModel;
    AddServiceMVP.ViewStepOne view;

    public AddServicePresenterImp(OmarApiModelInt apiModel, SharedPreferencesModelInt sharedPreferencesModel) {
        this.apiModel = apiModel;
        this.sharedPreferencesModel = sharedPreferencesModel;
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void servicesListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.MAIN_CATEGORIES_LIST){
             view.setMainCategoriesList((List<SkillDTO>)  event.getObject());
        }else if (event.getEventType() == CustomEventType.SUBCATEGORIES_LIST){
            view.setSubCategoriesList((List<SkillDTO>)  event.getObject());
        }
    }
    @Override
    public void register() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setView(AddServiceMVP.ViewStepOne view) {
        this.view=view;
    }

    @Override
    public void loadMainCategories() {
        apiModel.loadMainCategories();
    }

    @Override
    public void loadSubCategories(int skillId) {
         apiModel.loadSubCategoriesForCategory(skillId);
    }

}
