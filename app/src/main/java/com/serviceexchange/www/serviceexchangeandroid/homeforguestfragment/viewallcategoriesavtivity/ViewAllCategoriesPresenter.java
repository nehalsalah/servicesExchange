package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ViewAllCategoriesPresenter implements ViewAllMVP.Presenter {
    ViewAllMVP.View view;
    OmarApiModelInt apiModel;

    public ViewAllCategoriesPresenter(OmarApiModelInt apiModel) {
        this.apiModel = apiModel;
    }

    @Override
    public void setView(ViewAllMVP.View view) {
        this.view = view;
        EventBus.getDefault().register(this);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void reRegister() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void loadMainCategories() {
        apiModel.loadMainCategories();
    }

    @Override
    public void categoryCardOnClick(Integer id, String name) {
        view.startSubCategoriesActivity(id, name);
    }

    @Subscribe
    public void mainCategoriesListOnLoad(CustomEvent event) {
        if (event.getEventType() == CustomEventType.MAIN_CATEGORIES_LIST)
            view.setMainCategoriesList((List<SkillDTO>) event.getObject());
    }
}
