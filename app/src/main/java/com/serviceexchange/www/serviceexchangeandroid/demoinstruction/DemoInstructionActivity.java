package com.serviceexchange.www.serviceexchangeandroid.demoinstruction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class DemoInstructionActivity extends FragmentActivity {

    ViewPager viewPager;

    WormDotsIndicator wormDotsIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_instruction);

        viewPager = findViewById(R.id.viewPager);
        wormDotsIndicator = findViewById(R.id.worm_dots_indicator);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        wormDotsIndicator.setViewPager(viewPager);
    }
}
