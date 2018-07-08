package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class GuestHomePresenter implements GuestHomeMPV.Presenter {
    GuestHomeMPV.View view;
    OmarApiModelInt apiModelInt;

    public GuestHomePresenter(OmarApiModelInt apiModelInt) {
        this.apiModelInt = apiModelInt;
        EventBus.getDefault().register(this);
    }

    @Override
    public void setView(GuestHomeMPV.View view) {
        this.view = view;
    }

    @Override
    public void onSearchAction(String query) {

    }

    @Override
    public void loadMainCategoriesList() {
        apiModelInt.loadMainCategories();
    }

    @Override
    public void reRegister() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    @Override
    public void onPostEvent(CustomEvent event) {
        if (event.getEventType() == CustomEventType.MAIN_CATEGORIES_LIST) {
            view.setMainCategoriesList((List<SkillDTO>) event.getObject());
        } else if (event.getEventType() == CustomEventType.TOP_CATEGORIES_LIST) {
            view.setTopCategories((List<SkillDTO>) event.getObject());
        }
    }

    @Override
    public void loadTopCategoriesList(int length) {
        apiModelInt.loadTopCategories(length);
    }

    @Override
    public void categoryCardOnClick(Integer id, String name) {
        view.startSubCategoriesActivity(id, name);
    }

    @Override
    public void allCategoriesButtonAction() {
        view.startAllCategoriesActivity();
    }

}
