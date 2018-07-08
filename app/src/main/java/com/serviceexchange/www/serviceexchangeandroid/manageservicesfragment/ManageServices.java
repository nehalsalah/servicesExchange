package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serviceexchange.www.serviceexchangeandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageServices extends Fragment {

    ViewPager viewPager;

    public ManageServices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_services, container, false);

        viewPager = view.findViewById(R.id.pager);
        ManageServicesPagerAdapter adapter = new ManageServicesPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}
