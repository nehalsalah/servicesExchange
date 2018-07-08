package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.ServicesForCategoryActivity;

import java.util.List;

import javax.inject.Inject;

public class SubCategoriesActivity extends AppCompatActivity implements SubCategoryMVP.View {
    RecyclerView recyclerView;
    SubCategoryAdapter adapter;
    @Inject
    SubCategoryMVP.Presenter presenter;
    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);

        Toolbar toolbar = findViewById(R.id.toolbarForSubCategoriesActivity);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.subCategoriesRecyclerView);
        adapter = new SubCategoryAdapter(context, presenter);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        int categoryId = getIntent().getIntExtra("categoryId", 0);
        String categoryName = getIntent().getStringExtra("categoryName");
        toolbar.setTitle(categoryName);
        presenter.loadSubCategoriesList(categoryId);

    }

    @Override
    protected void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    public void showServicesActivity(Integer id) {
        Intent intent = new Intent(this, ServicesForCategoryActivity.class);
        intent.putExtra("categoryId", id);
        startActivity(intent);
    }

    @Override
    public void setSubCategoriesList(List<SkillDTO> object) {
        adapter.refreshList(object);
    }
}
