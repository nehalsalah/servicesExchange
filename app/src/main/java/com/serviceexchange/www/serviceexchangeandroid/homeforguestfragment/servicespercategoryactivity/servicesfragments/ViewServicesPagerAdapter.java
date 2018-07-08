package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.service_exchange.api_services.dao.dto.ServiceDTO;

import java.util.List;

public class ViewServicesPagerAdapter extends FragmentPagerAdapter {
    RequestedServicesFragment requestedServicesFragment = new RequestedServicesFragment();
    OfferedServicesFragment offeredServicesFragment = new OfferedServicesFragment();

    public ViewServicesPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = requestedServicesFragment;
                break;
            case 1:
                fragment = offeredServicesFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        String name = null;
        switch (position) {
            case 0:
                name = "Requested";
                break;
            case 1:
                name = "Offered";
                break;
        }
        return name;
    }

    public void setCategoryIdForFragment(int categoryIdForFragment) {
        offeredServicesFragment.setCategoryId(categoryIdForFragment);
        requestedServicesFragment.setCategoryId(categoryIdForFragment);
    }
}
