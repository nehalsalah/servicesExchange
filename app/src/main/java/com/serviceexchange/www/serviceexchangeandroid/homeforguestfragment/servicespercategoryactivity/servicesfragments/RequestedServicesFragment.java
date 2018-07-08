package com.serviceexchange.www.serviceexchangeandroid.homeforguestfragment.servicespercategoryactivity.servicesfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestedServicesFragment extends Fragment implements ServicesPerCategoryMVP.View {

    private int categoryId;

    @Inject
    ServicesPerCategoryMVP.Presenter presenter;

    private ServicesForCategoryAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    Context context;

    public RequestedServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requested_services, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);
        recyclerView = view.findViewById(R.id.requestedServicesForCategoryRecyclerView);
        adapter = new ServicesForCategoryAdapter(presenter, context);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        presenter.loadRequestedServicesList(categoryId);
        return view;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void setOfferedServicesList(List<ServiceDTO> object) {

    }

    @Override
    public void setRequestedServicesList(List<ServiceDTO> object) {
        adapter.refreshList(object);
    }

    @Override
    public void startServiceDetailFragment(ServiceDTO service) {

    }
}
