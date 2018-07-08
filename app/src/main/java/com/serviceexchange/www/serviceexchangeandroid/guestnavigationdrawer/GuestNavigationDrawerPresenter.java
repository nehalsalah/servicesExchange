package com.serviceexchange.www.serviceexchangeandroid.guestnavigationdrawer;

import android.support.annotation.Nullable;

import com.serviceexchange.www.serviceexchangeandroid.R;

public class GuestNavigationDrawerPresenter implements GuestNavigationDrawerMVP.Presenter {

    @Nullable
    GuestNavigationDrawerMVP.View view;
    GuestNavigationDrawerMVP.Model model;

    public GuestNavigationDrawerPresenter(GuestNavigationDrawerMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(GuestNavigationDrawerMVP.View view) {
        this.view = view;
    }

    @Override
    public void navigationItemClicked(int id) {
        if (view != null) {
            switch (id) {
                case R.id.Home:
                    view.showHomeFragment();
                    break;
                case R.id.JoinUs:
                    view.showJoinUsFragment();
                    break;
                case R.id.About:
                    view.showAboutFragment();
                    break;
                case R.id.TermOfService:
                    view.showTermOfServiceFragment();
                    break;
                case R.id.PolicyPrivacy:
                    view.showPolicyPrivacyFragment();
                    break;
                case R.id.Support:
                    view.showSupportFragment();
                    break;
            }
        }
    }
}
