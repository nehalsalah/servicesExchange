package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.completed;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedServicesFragment extends Fragment implements CompletedMVP.View{

    RecyclerView recyclerView;
    CompletedServicesAdapter adapter;

    @Inject
    CompletedMVP.Presenter presenter;
    @Inject
    Context context;

    public CompletedServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed_services, container, false);

        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);

        recyclerView = view.findViewById(R.id.completedServicesRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        adapter = new CompletedServicesAdapter(null, context, presenter);
        recyclerView.setAdapter(adapter);

        presenter.loadCompletedServices(0);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.reRegister();
        presenter.loadCompletedServices(0);
    }

    @Override
    public void onDestroy() {
        presenter.terminate();
        super.onDestroy();
    }

    @Override
    public void setList(List<TransactionDto> object) {
        adapter.refreshList(object);
    }
}
