package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active.ActiveServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.cancelled.CancelledServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed.CompletedServicesFragment;

public class ManageServicesPagerAdapter extends FragmentPagerAdapter {

    ActiveServicesFragment activeServicesFragment = new ActiveServicesFragment();
    CompletedServicesFragment completedServicesFragment = new CompletedServicesFragment();
    CancelledServicesFragment cancelledServicesFragment = new CancelledServicesFragment();


    public ManageServicesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = activeServicesFragment;
                break;
            case 1:
                fragment = cancelledServicesFragment;
                break;
            case 2:
                fragment = completedServicesFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        String title = null;
        switch (position) {
            case 0:
                title = "Active";
                break;
            case 1:
                title = "Pending";
                break;
            case 2:
                title = "Completed";
                break;
        }
        return title;
    }
}
