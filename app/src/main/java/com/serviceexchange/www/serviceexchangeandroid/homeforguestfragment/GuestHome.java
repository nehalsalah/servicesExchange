package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.service_exchange.api_services.dao.dto.SkillDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.subcategoriesactivity.SubCategoriesActivity;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.viewallcategoriesavtivity.ViewAllCategoriesActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestHome extends Fragment implements GuestHomeMPV.View {

    private MaterialSearchBar searchBar;
    private RecyclerView categoryRecyclerView;
    private RecyclerView topcategoryRecyclerView;
//    private RecyclerView popularServicesRecyclerView;
    private Button allCategoriesButton;
    private CategoryAdapter adapter;
    private TopCategoryAdapter topCategoryAdapter;

    @Inject
    GuestHomeMPV.Presenter presenter;
    @Inject
    Context context;

    public GuestHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guest_home, container, false);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);

        searchBar = view.findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(new SearchActionListener());

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(categoryRecyclerView);
        adapter = new CategoryAdapter(null, context, presenter);
        categoryRecyclerView.setAdapter(adapter);
        categoryRecyclerView.setNestedScrollingEnabled(true);

        topcategoryRecyclerView = view.findViewById(R.id.topCategoriesRecyclerView);
        layoutManager = new LinearLayoutManager(context);
        topcategoryRecyclerView.setLayoutManager(layoutManager);
        topCategoryAdapter = new TopCategoryAdapter(null, context, presenter);
        topcategoryRecyclerView.setAdapter(topCategoryAdapter);
        topcategoryRecyclerView.setNestedScrollingEnabled(true);

//        popularServicesRecyclerView = view.findViewById(R.id.popularServicesRecyclerView);
//        layoutManager = new LinearLayoutManager(context);
//        popularServicesRecyclerView.setLayoutManager(layoutManager);
//        PopularServiceAdapter popularServiceAdapter = new PopularServiceAdapter(new ArrayList<Service>(), context, presenter);
//        popularServicesRecyclerView.setAdapter(popularServiceAdapter);
//        popularServicesRecyclerView.setNestedScrollingEnabled(true);

        allCategoriesButton = view.findViewById(R.id.allCategoriesButton);
        allCategoriesButton.setText(Html.fromHtml("View All &#10148;"));
        allCategoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.allCategoriesButtonAction();
            }
        });

        // load from api
        presenter.loadMainCategoriesList();
        presenter.loadTopCategoriesList(3);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.reRegister();
    }

    @Override
    public void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    public void setMainCategoriesList(List<SkillDTO> object) {
        adapter.refreshList(object);
    }

    @Override
    public void setTopCategories(List<SkillDTO> object) {
        topCategoryAdapter.refreshList(object);
    }

    @Override
    public void startSubCategoriesActivity(Integer id, String name) {
        Intent intent = new Intent(getContext(), SubCategoriesActivity.class);
        intent.putExtra("categoryId", id);
        intent.putExtra("categoryName", name);
        startActivity(intent);
    }

    @Override
    public void startAllCategoriesActivity() {
        Intent intent = new Intent(getContext(), ViewAllCategoriesActivity.class);
        startActivity(intent);
    }

    private class SearchActionListener implements MaterialSearchBar.OnSearchActionListener {

        @Override
        public void onSearchStateChanged(boolean enabled) {

        }

        @Override
        public void onSearchConfirmed(CharSequence text) {
            // do the search logic here
            presenter.onSearchAction(text.toString());
        }

        @Override
        public void onButtonClicked(int buttonCode) {

        }
    }
}