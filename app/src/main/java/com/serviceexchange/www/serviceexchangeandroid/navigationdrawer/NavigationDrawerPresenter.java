package com.serviceexchange.www.serviceexchangeandroid.navigationdrawer;

import android.support.annotation.Nullable;

import com.service_exchange.api_services.dao.dto.UserDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModelInt;

public class NavigationDrawerPresenter implements NavigationDrawerMVP.Presenter {

    @Nullable
    NavigationDrawerMVP.View view;
    NavigationDrawerMVP.Model model;

    private SharedPreferencesModelInt preferencesModel;

    public NavigationDrawerPresenter(SharedPreferencesModelInt preferencesModel) {
        this.preferencesModel = preferencesModel;
    }

    @Override
    public void setView(NavigationDrawerMVP.View view) {
        this.view = view;
    }

    @Override
    public void navigationItemClicked(int id) {
        if (view != null) {
            switch (id) {
                case R.id.MyStats:
                    view.showMyStatsFragment();
                    break;
                case R.id.Home:
                    view.showHomeFragment();
                    break;
                case R.id.Inbox:
                    view.showInboxFragment();
                    break;
                case R.id.Notification:
                    view.showNotificationFragment();
                    break;
                case R.id.MyRequests:
                    view.showMyRequestsFragment();
                    break;
                case R.id.ManageService:
                    view.showManageServiceFragment();
                    break;
                case R.id.Earning:
                    view.showEarningFragment();
                    break;
                case R.id.MyService:
                    view.showMyServiceFragment();
                    break;
                case R.id.ServiceRequests:
                    view.showShowServiceRequestsFragment();
                    break;
                case R.id.ShareService:
                    view.showShareServiceFragment();
                    break;
                case R.id.Profile:
                    view.showProfileFragment();
                    break;
                case R.id.Logout:
                    view.showLogoutFragment();
                    break;
            }
        }
    }

    @Override
    public void statusSwitchClicked(boolean isChecked) {
        if (view != null) {
            view.onlineStatusAction(isChecked);
        }
    }

    @Override
    public UserDTO getCurrentUser() {
        return preferencesModel.getCurrentUserFromSharedPreferences();
    }
}
