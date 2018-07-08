package com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.active;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.serviceexchange.www.serviceexchangeandroid.R;
import com.serviceexchange.www.serviceexchangeandroid.application.App;
import com.serviceexchange.www.serviceexchangeandroid.manageservicesfragment.activetransactiondetailactivity.ActiveTransactionDetailActivity;
import com.serviceexchange.www.serviceexchangeandroid.pojos.TransactionDto;

import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveServicesFragment extends Fragment implements ActiveMVP.View {

    @Inject
    ActiveMVP.Presenter presenter;
    @Inject
    Context context;

    RecyclerView recyclerView;
    ActiveServicesAdapter adapter;

    public ActiveServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active_services, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.setView(this);

        recyclerView = view.findViewById(R.id.activeServicesRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        adapter = new ActiveServicesAdapter(null, context, presenter);
        recyclerView.setAdapter(adapter);

        presenter.loadActiveServices(0);

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
    public void showServiceDetailsView(TransactionDto trans) {
        Intent intent = new Intent(getContext(), ActiveTransactionDetailActivity.class);
        Log.i("timeline", "before going"+trans.isServiceProvider());
        intent.putExtra("trans", trans);
        startActivity(intent);
    }

    @Override
    public void setList(List<TransactionDto> object) {
        adapter.refreshList(object);
    }
}
