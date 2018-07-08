package com.serviceexchange.www.serviceexchangeandroid.myrequests;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.service_exchange.api_services.dao.dto.ServiceDTO;
import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.myrequests.addrquests.AddRequestsActivity;
import com.serviceexchange.www.serviceexchangeandroid.sharedprefrencesmodel.SharedPreferencesModel;

import java.util.List;

import javax.inject.Inject;

public class MyRequestsFragment extends Fragment implements MyRequestsMVP.View {

    @Inject
    MyRequestsMVP.Presenter presenter;

    @Inject
    Context context;

    private RecyclerView recyclerView;
    private MyRequestsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton newrequest_floatingActionButton;

    private SharedPreferencesModel sharedPreferencesModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_myrequests, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);

        presenter.setView(this);
        recyclerView = fragmentView.findViewById(R.id.myrequests_recyclerview);

        newrequest_floatingActionButton = fragmentView.findViewById(R.id.newrequest_floatingActionButton);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyRequestsAdapter(presenter, context);

        recyclerView.setAdapter(adapter);

        sharedPreferencesModel = SharedPreferencesModel.getInstance(context);

        presenter.loadAllMyRequests(sharedPreferencesModel.getCurrentUserFromSharedPreferences().getId());

        newrequest_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AddRequestsIntent = new Intent(context, AddRequestsActivity.class);
                startActivity(AddRequestsIntent);
            }
        });
        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.register();
        presenter.loadAllMyRequests(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.terminate();
    }

    @Override
    public void goToWhoRequest() {
        //TODO go to request offer list
    }

    @Override
    public void setServiceList(List<ServiceDTO> serviceDTOList) {
        adapter.refreshList(serviceDTOList);
    }
}
