package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.apimodels.modelsinterfaces.OmarApiModelInt;
import com.serviceexchange.www.serviceexchangeandroid.loginmodule.CustomEvent;
import com.serviceexchange.www.serviceexchangeandroid.utils.CustomEventType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class SubCategoriesPresenter implements SubCategoryMVP.Presenter {

    SubCategoryMVP.View view;
    OmarApiModelInt apiModel;

    public SubCategoriesPresenter(OmarApiModelInt apiModel) {
        this.apiModel = apiModel;
    }

    @Override
    public void setView(SubCategoryMVP.View view) {
        this.view = view;
        EventBus.getDefault().register(this);
    }

    @Override
    public void cardOnClick(Integer id) {
        view.showServicesActivity(id);
    }

    @Override
    public void terminate() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadSubCategoriesList(int categoryId) {
        apiModel.loadSubCategoriesForCategory(categoryId);
    }

    @Subscribe
    public void subCategoriesListOnload(CustomEvent event) {
        if (event.getEventType() == CustomEventType.SUBCATEGORIES_LIST)
            view.setSubCategoriesList((List<SkillDTO>) event.getObject());
    }
}
