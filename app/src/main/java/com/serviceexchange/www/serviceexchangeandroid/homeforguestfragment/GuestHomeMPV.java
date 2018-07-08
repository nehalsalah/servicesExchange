package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.utils.BasePresenter;

import java.util.List;

public interface GuestHomeMPV {

    public interface View {
        void setMainCategoriesList(List<SkillDTO> object);

        void setTopCategories(List<SkillDTO> object);

        void startSubCategoriesActivity(Integer id, String name);

        void startAllCategoriesActivity();
    }

    public interface Presenter extends BasePresenter {

        void setView(View view);
        void onSearchAction(String query);

        void loadMainCategoriesList();

        void terminate();

        void loadTopCategoriesList(int length);

        void categoryCardOnClick(Integer id, String name);

        void allCategoriesButtonAction();
    }
}
