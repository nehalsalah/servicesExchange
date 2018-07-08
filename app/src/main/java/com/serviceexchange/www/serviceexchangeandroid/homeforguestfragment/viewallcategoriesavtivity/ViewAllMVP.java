package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity;

import com.service_exchange.api_services.dao.dto.SkillDTO;

import java.util.List;

public interface ViewAllMVP {

    interface View {

        void setMainCategoriesList(List<SkillDTO> object);

        void startSubCategoriesActivity(Integer id, String name);
    }

    interface Presenter {
        void setView(View view);
        void terminate();
        void reRegister();

        void loadMainCategories();

        void categoryCardOnClick(Integer id, String name);
    }
}
