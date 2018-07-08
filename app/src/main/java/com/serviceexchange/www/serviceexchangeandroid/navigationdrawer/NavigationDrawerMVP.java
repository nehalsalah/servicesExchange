package com.serviceexchange.www.serviceexchangeandroid.navigationdrawer;

import com.service_exchange.api_services.dao.dto.UserDTO;

public interface NavigationDrawerMVP {

    interface View {
        void showMyStatsFragment();

        void showHomeFragment();

        void showInboxFragment();

        void showNotificationFragment();

        void showMyRequestsFragment();

        void onlineStatusAction(boolean isChecked);

        void showManageServiceFragment();

        void showEarningFragment();

        void showMyServiceFragment();

        void showShowServiceRequestsFragment();

        void showShareServiceFragment();

        void showLogoutFragment();

        void showProfileFragment();
    }

    interface Presenter {
        void setView(NavigationDrawerMVP.View view);

        void navigationItemClicked(int id);

        void statusSwitchClicked(boolean isChecked);

        UserDTO getCurrentUser();
    }

    interface Model {

    }
}
