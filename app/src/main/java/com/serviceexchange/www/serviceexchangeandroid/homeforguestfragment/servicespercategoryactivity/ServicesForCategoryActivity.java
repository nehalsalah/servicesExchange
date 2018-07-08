package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.OfferedServicesFragment;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments.ViewServicesPagerAdapter;

import java.util.List;

import javax.inject.Inject;

public class ServicesForCategoryActivity extends AppCompatActivity {

    @Inject
    Context context;

//    ViewPager viewPager;
//    ViewServicesPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_for_category);

        ((App) getApplication()).getComponent().inject(this);

        Toolbar toolbar = findViewById(R.id.toolbarForServicesActivity);
        setSupportActionBar(toolbar);

//        recyclerView = findViewById(R.id.servicesForCategoryRecyclerView);
//        adapter = new ServicesForCategoryAdapter(presenter, context);
//        recyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
//        recyclerView.setLayoutManager(manager);

//        viewPager = findViewById(R.id.servicesPager);
//        adapter = new ViewServicesPagerAdapter(getSupportFragmentManager());
//        int categoryId = getIntent().getIntExtra("categoryId", 0);
//        adapter.setCategoryIdForFragment(categoryId);
//        viewPager.setAdapter(adapter);
//        TabLayout tabLayout = findViewById(R.id.servicesTab);
//        tabLayout.setupWithViewPager(viewPager);
        OfferedServicesFragment fragment = new OfferedServicesFragment();
        int categoryId = getIntent().getIntExtra("categoryId", 0);
        fragment.setCategoryId(categoryId);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.servicesForCategoryContainer, fragment)
                .commit();
    }
}
