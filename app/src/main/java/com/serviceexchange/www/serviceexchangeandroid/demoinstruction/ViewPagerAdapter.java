package com.serviceexchange.www.serviceexchangeandroid.demoinstruction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstDemoInstructionFragment();
            case 1:
                return new SecondDemoInstructionFragment();
            case 2:
                return new ThirdDemoInstructionFragment();
            case 3:
                return new FourthDemoInstructionFragment();
            default:
                return new FirstDemoInstructionFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
