package com.serviceexchange.www.serviceexchangeandroid.guestnavigationdrawer;

public interface GuestNavigationDrawerMVP {

    interface View {
        void showHomeFragment();

        void showJoinUsFragment();

        void showAboutFragment();

        void showTermOfServiceFragment();

        void showPolicyPrivacyFragment();

        void showSupportFragment();
    }

    interface Presenter {
        void setView(GuestNavigationDrawerMVP.View view);

        void navigationItemClicked(int id);
    }

    interface Model {

    }
}
