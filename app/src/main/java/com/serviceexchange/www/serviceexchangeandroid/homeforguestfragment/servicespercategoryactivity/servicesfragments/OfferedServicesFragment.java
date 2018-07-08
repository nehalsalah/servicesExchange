package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicedetailfragment.ServiceDetailFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfferedServicesFragment extends Fragment implements ServicesPerCategoryMVP.View {

    private int categoryId;

    private ServicesForCategoryAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    ServicesPerCategoryMVP.Presenter presenter;
    @Inject
    Context context;

    public OfferedServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offered_services, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        recyclerView = view.findViewById(R.id.offeredServicesForCategoryRecyclerView);
        adapter = new ServicesForCategoryAdapter(presenter, context);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        presenter.loadOfferedServicesList(categoryId, 0);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.reRegister();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void setOfferedServicesList(List<ServiceDTO> object) {
        adapter.refreshList(object);
    }

    @Override
    public void setRequestedServicesList(List<ServiceDTO> object) {
    }

    @Override
    public void startServiceDetailFragment(ServiceDTO service) {
        ServiceDetailFragment fragment = ServiceDetailFragment.getInstance(service);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.serviceDetailFragmentContainer, fragment)
                .commit();
    }
}
