package com.serviceexchange.www.serviceexchangeandroid.myService.addSevice.StepOne;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.myService.MyServiceMVP;

import java.util.List;
import java.util.Map;

public interface AddServiceMVP {
    interface ViewStepOne {

        void addService();
        void editService();
        void setMainCategoriesList(List<SkillDTO> object);
        void setSubCategoriesList(List<SkillDTO> object);

    }

    interface Presenter {
        void loadMainCategories();
        void loadSubCategories(int skillId);

        void register();

        void terminate();

        void setView(AddServiceMVP.ViewStepOne view);

    }
    interface Model {

    }
}
