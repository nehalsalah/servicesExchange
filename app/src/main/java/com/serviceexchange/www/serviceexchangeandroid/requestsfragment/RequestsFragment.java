package com.serviceexchange.www.serviceexchangeandroid.requestsfragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.requestsfragment.offerdetailfragment.OfferDetailActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment implements RequestsMVP.View {

    RecyclerView recyclerView;
    RequestsAdapter adapter;

    @Inject
    RequestsMVP.Presenter presenter;
    @Inject
    Context context;

    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);

        recyclerView = view.findViewById(R.id.requestsRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new RequestsAdapter(null, context, presenter);
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        presenter.loadAllRequests(0);

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
    public void showOfferDetailsFragment(ServiceDTO service) {
        Intent intent = new Intent(context, OfferDetailActivity.class);
        intent.putExtra("service", service);
        startActivity(intent);
    }

    @Override
    public void setList(List<ServiceDTO> object) {
        adapter.refreshList(object);
    }
}
