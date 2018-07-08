package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity;

import com.service_exchange.api_services.dao.dto.SkillDTO;

import java.util.List;

public interface SubCategoryMVP {
    
    interface View {

        void showServicesActivity(Integer id);

        void setSubCategoriesList(List<SkillDTO> object);
    }
    
    interface Presenter {
        void setView(View view);
        void cardOnClick(Integer id);

        void terminate();

        void loadSubCategoriesList(int categoryId);
    }
}
