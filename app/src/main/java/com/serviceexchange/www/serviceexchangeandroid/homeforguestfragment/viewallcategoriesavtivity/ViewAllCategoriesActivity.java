package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.TopCategoryAdapter;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity.SubCategoriesActivity;

import java.util.List;

import javax.inject.Inject;

public class ViewAllCategoriesActivity extends AppCompatActivity implements ViewAllMVP.View {

    @Inject
    ViewAllMVP.Presenter presenter;
    @Inject
    Context context;

    MainCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_categories);
        ((App) getApplication()).getComponent().inject(this);
        presenter.setView(this);
        RecyclerView recyclerView = findViewById(R.id.allMainCategoriesRecyclerView);
        adapter = new MainCategoryAdapter(context, presenter);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        presenter.loadMainCategories();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.reRegister();
    }

//    @Override
//    protected void onDestroy() {
//        presenter.terminate();
//        super.onDestroy();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.terminate();
    }

    @Override
    public void setMainCategoriesList(List<SkillDTO> object) {
        adapter.refreshList(object);
    }

    @Override
    public void startSubCategoriesActivity(Integer id, String name) {
        Intent intent = new Intent(context, SubCategoriesActivity.class);
        intent.putExtra("categoryId", id);
        intent.putExtra("categoryName", name);
        startActivity(intent);
    }
}
